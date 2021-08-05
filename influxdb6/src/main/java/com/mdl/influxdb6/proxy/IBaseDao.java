package com.mdl.influxdb6.proxy;

import com.mdl.influxdb6.measurements.TicBaseMeasurement;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:21
 * ----------------- ----------------- -----------------
 */

public interface IBaseDao<T extends TicBaseMeasurement> {

  void save(T t);

  void get(Long id);

  void delete(Long id);

}
