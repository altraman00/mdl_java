package com.mdl.influxdb4.proxy;

import static jdk.nashorn.internal.objects.Global.print;

import com.mdl.influxdb4.annotations.Influxhql;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4.proxy
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月30日 16:20
 * ----------------- ----------------- -----------------
 */

public class TicBaseRepositoryJDKDynamicProxy implements InvocationHandler {

  @Autowired
  private InfluxDB influxDB;

  private Object target;

  public TicBaseRepositoryJDKDynamicProxy(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("判断用户是否有权限进行操作11111");
//    Object obj = method.invoke(target);
    Object arg = args[0];
    Class<?> aClass = target.getClass();
    Class<?> superclass = aClass.getSuperclass();

    Type genericSuperclass = aClass.getGenericSuperclass();
    ParameterizedType paramType = (ParameterizedType)genericSuperclass;
    //获取泛型类型
    Type[] types = paramType.getActualTypeArguments();
    System.out.println(types[0].getTypeName());


//    Type[] genericInterfaces = aClass.getGenericInterfaces();
//    Arrays.stream(genericInterfaces).forEach(System.out::println);
//    int length = genericInterfaces.length;
//    for (int i = 0; i < length; i++) {
//      Type genericInterface = genericInterfaces[i];
//      System.out.println(genericInterface.getTypeName());
//
//      if (genericInterface instanceof ParameterizedType) {
//        print((ParameterizedType) genericInterface);
//      }
//
//      System.out.println("\n----------------------------------------");
//      // 父类泛型类
//      Type genericSuperclass = aClass.getGenericSuperclass();
//      System.out.println(genericSuperclass.getTypeName());
//      if (genericSuperclass instanceof ParameterizedType) {
//        print((ParameterizedType) genericSuperclass);
//      }
//    }






//    boolean customFlag = isInfluxsqlAnnotation(method);
//    if (customFlag) {
//      dealCustomFunc(method, arg);
//    } else {
//      dealDefaultFunc(aClass, method, arg);
//    }
//
//    dealCustomFunc(method, arg);
//
//    System.out.println("------>接口方法调用结束");

    return "调用返回值";
  }

  private static void print(ParameterizedType parameterizedType) {
    System.out.println(parameterizedType.getOwnerType());
    System.out.println(parameterizedType.getRawType());
    System.out.print("泛型参数：");
    Arrays.stream(parameterizedType.getActualTypeArguments()).forEach(arg -> System.out.print(arg + "、"));
  }


  /**
   * 判断是否有influxhql注解
   */
  private boolean isInfluxsqlAnnotation(Method method) {
    Annotation[] annotations = method.getAnnotations();
    return Arrays.stream(annotations).findAny()
        .filter(annotation ->
            annotation
                .annotationType()
                .getSimpleName().equals("Influxhql") ? true : false)
        .isPresent();
  }

  /**
   * 默认方法的执行过程
   */
  private void dealDefaultFunc(Class<?> aClass, Method method, Object arg) {
    String name = aClass.getName();
    Field[] declaredFields = aClass.getDeclaredFields();
  }

  /**
   * 如果是使用的自定义注解@Influxhql，则处理定义的hql语句并返回值
   */
  private void dealCustomFunc(Method method, Object arg) {
    Influxhql annotation = method.getAnnotation(Influxhql.class);
    String database = annotation.database();
    String measurement = annotation.measurement();
    boolean nativeQueryFlag = annotation.nativeQuery();
    String hql = annotation.value();

    System.out.println("------>接口方法调用开始");
    //执行方法
    System.out.println("method toGenericString:" + method.toGenericString());
    System.out.println("method name:" + method.getName());
    System.out.println("method args:" + arg);

    Query query = new Query(hql, database);
//    influxDB.query(query, clazz);
//    QueryResult queryRes = influxDB.query(query);
//    List<Result> results = queryRes.getResults();

  }


  public static <T> T newMapperProxy(Class<T> targerInterface) {
    ClassLoader classLoader = targerInterface.getClassLoader();
    Class<?>[] interfaces = new Class[]{targerInterface};
    TicBaseRepositoryJDKDynamicProxy proxy = new TicBaseRepositoryJDKDynamicProxy(targerInterface);
    return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
  }


}
