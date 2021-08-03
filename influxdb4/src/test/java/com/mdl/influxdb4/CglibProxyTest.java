package com.mdl.influxdb4;

import com.mdl.influxdb4.proxy.TicBaseRepositoryCglibProxy;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月30日 16:02
 * ----------------- ----------------- -----------------
 */
public class CglibProxyTest {

  public static void main(String[] args) {
    AdminCglibService target = new AdminCglibService();
    TicBaseRepositoryCglibProxy proxyFactory = new TicBaseRepositoryCglibProxy(target);
    AdminCglibService proxy = (AdminCglibService) proxyFactory.getProxyInstance();

//    System.out.println("代理对象：" + proxy.getClass());
    Object obj = proxy.find();
//    System.out.println("find 返回对象：" + obj.getClass());

    System.out.println("----------------------------------");

    proxy.update();
  }

}
