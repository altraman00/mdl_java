package com.mdl.influxdb4.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4.proxydemo
 * @Author : xiekun
 * @Desc : 参考：https://blog.csdn.net/u012240455/article/details/79210250
 * @Create Date : 2021年08月02日 22:46
 * ----------------- ----------------- -----------------
 */
public class FacadeProxy implements InvocationHandler {

  /**
   * 这个就是我们要代理的真实对象
   */
  private Object subject;

  public FacadeProxy(Object subject) {
    this.subject = subject;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
      throws Throwable {
    System.out.println("接口方法调用开始");
    //执行方法
    System.out.println("method toGenericString:" + method.toGenericString());
    System.out.println("method name:" + method.getName());
    System.out.println("method args:" + (String) args[0]);
    System.out.println("接口方法调用结束");
    return "【我是代理调用后的返回值】";
  }

  public static <T> T newMapperProxy(Class<T> mapperInterface) {
    ClassLoader classLoader = mapperInterface.getClassLoader();
    Class<?>[] interfaces = new Class[]{mapperInterface};
    FacadeProxy proxy = new FacadeProxy(mapperInterface);
    return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
  }

}
