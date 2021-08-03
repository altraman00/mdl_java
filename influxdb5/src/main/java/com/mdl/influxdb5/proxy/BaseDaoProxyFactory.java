package com.mdl.influxdb5.proxy;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc : dao动态代理工厂类
 * @Create Date : 2021年08月03日 15:15
 * ----------------- ----------------- -----------------
 */
public class BaseDaoProxyFactory<T> implements FactoryBean<T> {

  /**
   * 代理dao 接口的class
   */
  private Class<T> interfaceClass;

  public Class<T> getInterfaceClass() {
    return interfaceClass;
  }

  public void setInterfaceClass(Class<T> interfaceClass) {
    this.interfaceClass = interfaceClass;
  }

  @Override
  public T getObject() throws Exception {
    return (T) new BaseDaoProxy<T>().bind(interfaceClass);
  }

  @Override
  public Class<?> getObjectType() {
    return interfaceClass;
  }

  /**
   * 对象是否是单例
   * true 每次获取对象都是同一个对象
   * false 每次获取都是新的对象
   * @return
   */
  @Override
  public boolean isSingleton() {
    return true;
  }

}
