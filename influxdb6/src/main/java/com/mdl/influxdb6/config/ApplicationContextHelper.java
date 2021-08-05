package com.mdl.influxdb6.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.config
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月05日 22:41
 * ----------------- ----------------- -----------------
 */

@Component
public class ApplicationContextHelper implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    applicationContext = context;
  }

  /**
   * 获取bean
   */
  public static <T> T popBean(Class<T> clazz) {
    //先判断是否为空
    if (applicationContext == null) {
      return null;
    }
    return applicationContext.getBean(clazz);
  }


  public static <T> T popBean(String name, Class<T> clazz) {
    if (applicationContext == null) {
      return null;
    }
    return applicationContext.getBean(name, clazz);
  }

}
