package com.mdl.influxdb5.proxy;

import com.mdl.influxdb5.measurements.TicBaseMeasurement;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:22
 * ----------------- ----------------- -----------------
 */
public class BaseDaoImpl<T extends TicBaseMeasurement> implements IBaseDao {

  @Override
  public void save(TicBaseMeasurement ticBaseMeasurement) {
    System.out.println("=========DB save()执行==========");
  }

  @Override
  public void get(Long id) {
    System.out.println("=========DB get()执行==========");
  }

  @Override
  public void delete(Long id) {
    System.out.println("=========DB delete()执行===============");
  }

  /**
   * 自定义注解的通用操作方法
   */
  public String dealHqlFunc(BaseDaoObj baseDaoObj) {
    System.out.println("=========DB doQuery()执行统一的deal方法==========");
    return baseDaoObj.getHql();
  }

}
