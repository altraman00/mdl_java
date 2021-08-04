package com.mdl.influxdb5.proxy;

import com.mdl.influxdb5.annotations.InfluxDbHql;
import com.mdl.influxdb5.annotations.InfluxDbHqlMapper;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.influxdb.annotation.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:14
 * ----------------- ----------------- -----------------
 */
public class BaseDaoProxy<T> implements InvocationHandler {

  private static final Logger logger = LoggerFactory.getLogger(BaseDaoProxy.class);

  /**
   * dao的class
   */
  private Class<T> interfaceClass;

  public Object bind(Class<T> cls) {
    this.interfaceClass = cls;
    return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{interfaceClass}, this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (Object.class.equals(method.getDeclaringClass())) {
      return method.invoke(this, args);
    }

    Method baseDaoMethod;
    BaseDaoObj baseDaoObj;
    if (isInfluxsqlAnnotation(method)) {
      //没有具体方法实现类的就使用通用的方法
      baseDaoMethod = BaseDaoImpl.class.getMethod("dealHqlFunc", BaseDaoObj.class);
      baseDaoObj = dealInfluxdbHql(method, args);
    } else {
      //通过反射获取当前执行的方法对应的BaseDaoImpl方法
      baseDaoMethod = BaseDaoImpl.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
      baseDaoObj = dealNotInfluxdbHql(method, args);
    }

    //执行反射获取的BaseDaoImpl方法
    return baseDaoMethod.invoke(new BaseDaoImpl(), baseDaoObj);
  }


  /**
   * 判断是否有influxhql注解
   */
  private boolean isInfluxsqlAnnotation(Method method) {
    Annotation[] annotations = method.getAnnotations();
    String simpleName = InfluxDbHql.class.getSimpleName();
    return Arrays.stream(annotations).findAny()
        .filter(annotation ->
            annotation
                .annotationType()
                .getSimpleName().equals(simpleName) ? true : false)
        .isPresent();
  }

  /**
   * 默认方法的执行过程
   */
  private BaseDaoObj dealNotInfluxdbHql(Method method, Object arg) {
    return new BaseDaoObj();
  }

  /**
   * 如果是使用的自定义注解@Influxhql，则处理定义的hql语句并返回值
   */
  private BaseDaoObj dealInfluxdbHql(Method method, Object arg) throws Exception {

    logger.debug("method toGenericString:{},method name:{},method args:{}",method.toGenericString(),method.getName(),arg);

    //通过反射获取当前执行的方法对应的BaseDaoImpl方法
    InfluxDbHql annotation = method.getAnnotation(InfluxDbHql.class);

    //hql语句
    String hql = annotation.value();
    boolean nativeQueryFlag = annotation.nativeQuery();
    logger.info("[dealHqlFunc] hql:{},nativeQueryFlag:{}",hql,nativeQueryFlag);

    Class<?> daoSubClass = method.getDeclaringClass();
    String daoSubClassPath = daoSubClass.getName();
    String daoSubClassName = daoSubClass.getSimpleName();
    Type daoSuperclass = daoSubClass.getGenericSuperclass();
    logger.debug("[daoSubClass]daoSubClassPath:{},daoSuperclass:{}",daoSubClassPath,daoSuperclass);

    InfluxDbHqlMapper declaredAnnotation = daoSubClass.getDeclaredAnnotation(InfluxDbHqlMapper.class);
    String database = declaredAnnotation.database();
    String measurement = declaredAnnotation.measurement();
    logger.debug("[InfluxDbHqlMapper]database:{},measurement:{}",database,measurement);

    Type[] superInterfaces = daoSubClass.getGenericInterfaces();
    //父类接口
    Type superGenericInterface = superInterfaces[0];
    //父类泛型全路径
    String superTypeName = superGenericInterface.getTypeName();
    ParameterizedType parameterizedType = (ParameterizedType) superGenericInterface;
    //父类
    Type superRawType = parameterizedType.getRawType();
    logger.debug("[superClass]superTypeName:{},superRawType:{}",superTypeName,superRawType);

    Type[] superActualTypeArguments = parameterizedType.getActualTypeArguments();
    //父类泛型的class
    Class superActualTypeArgument = (Class) superActualTypeArguments[0];
    //父类泛型的名称
    String superClassGenericSimpleName = superActualTypeArgument.getSimpleName();
    //泛型的包路径
    String superClassGenericPackagePath = superActualTypeArgument.getName();
    logger.debug("[superClassGeneric]superClassGenericSimpleName:{},superClassGenericPackagePath:{}"
        ,superClassGenericSimpleName,superClassGenericPackagePath);

    //根据泛型的measurement的注解获取database和measurement
    Object newInstance = superActualTypeArgument.newInstance();
    Class<?> genericClass = newInstance.getClass();
    String measurementName = genericClass.getDeclaredAnnotation(Measurement.class).name();
    String measurementDatabase = genericClass.getDeclaredAnnotation(Measurement.class).database();
    logger.info("[superClassGeneric]measurementName:{},measurementDatabase:{}"
        ,measurementName,measurementDatabase);

    if(StringUtils.isEmpty(measurementName) || StringUtils.isEmpty(measurementDatabase)){
      throw new Exception("measurement entity miss default config like database or measurement");
    }

    BaseDaoObj baseDaoObj = new BaseDaoObj();
    baseDaoObj.setArg(arg);
    baseDaoObj.setHql(hql);
    baseDaoObj.setMethod(method.getName());
    baseDaoObj.setDatabase(measurementDatabase);
    baseDaoObj.setMeasurement(measurementName);
    baseDaoObj.setClassPath(daoSubClassPath);
    baseDaoObj.setClassName(daoSubClassName);
    return baseDaoObj;
  }

}
