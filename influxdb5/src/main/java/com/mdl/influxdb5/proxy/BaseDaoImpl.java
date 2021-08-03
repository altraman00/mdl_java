package com.mdl.influxdb5.proxy;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:22
 * ----------------- ----------------- -----------------
 */
public class BaseDaoImpl implements IBaseDao {

  @Override
  public void get(Long id) {
    //数据库的查询操作方法
    System.out.println("=========DB get()执行==========");
  }

  @Override
  public void delete(Long id) {
    //数据库的删除方法
    System.out.println("=========DB delete()执行===============");
  }

  public String getById(Long id) {
    //数据库的查询操作方法
    System.out.println("=========DB getById()执行==========");
    return "getById";
  }

  /**
   * 统一的查询方法
   */
  public String doQuery(Long id) {
    System.out.println("=========DB doQuery()执行统一的查询方法==========");
    return id + "执行统一的查询方法并返回对应的值";
  }

}
