package com.mdl.influxdb4.proxydemo;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4.proxydemo
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月02日 22:49
 * ----------------- ----------------- -----------------
 */
public class HelloImpl implements IHello {

  @Override
  public String say(String aa) {
    System.out.println("hello impl");
    return "say hello impl";
  }
}
