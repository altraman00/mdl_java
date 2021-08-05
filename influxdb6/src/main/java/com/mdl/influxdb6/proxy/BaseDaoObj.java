package com.mdl.influxdb6.proxy;

import lombok.Data;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.proxy
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月04日 17:26
 * ----------------- ----------------- -----------------
 */
@Data
public class BaseDaoObj {

  private String database;

  private String measurement;

  private String hql;

  private Object arg;

  private String method;

  private String className;

  private String classPath;

  private String measurementClassName;

  private Class measurementClass;

}
