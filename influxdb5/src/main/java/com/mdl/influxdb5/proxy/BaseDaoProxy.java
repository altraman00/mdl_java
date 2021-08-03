package com.mdl.influxdb5.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:14
 * ----------------- ----------------- -----------------
 */
public class BaseDaoProxy<T> implements InvocationHandler {

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

    IBaseDao dao = new BaseDaoImpl();
    //通过反射获取当前执行的方法对应的BaseDaoImpl方法
//    Method baseDaoMethod = dao.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());

    //没有具体方法实现类的就使用通用的方法
    Method baseDaoMethod = dao.getClass().getDeclaredMethod("doQuery", method.getParameterTypes());
    //执行反射获取的BaseDaoImpl方法
    return baseDaoMethod.invoke(dao, args);

  }
}
