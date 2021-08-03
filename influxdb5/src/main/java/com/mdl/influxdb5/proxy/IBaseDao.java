package com.mdl.influxdb5.proxy;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:21
 * ----------------- ----------------- -----------------
 */
public interface IBaseDao {

  /**
   * 模拟查询
   */
  void get(Long id);

  /**
   * 模拟删除
   */
  void delete(Long id);

}
