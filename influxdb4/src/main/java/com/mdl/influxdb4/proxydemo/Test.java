package com.mdl.influxdb4.proxydemo;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4.proxydemo
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月02日 22:47
 * ----------------- ----------------- -----------------
 */
public class Test {

  public static void main(String[] args) {
    IHello hello = FacadeProxy.newMapperProxy(IHello.class);
    String hello_world = hello.say("hello world");
    System.out.println("proxy result:" + hello_world);
  }
}
