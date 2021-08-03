package com.mdl.influxdb4.repository.base;

import com.mdl.influxdb4.measurements.TicBaseMeasurement;
import java.util.List;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4.repository.base
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月30日 16:26
 * ----------------- ----------------- -----------------
 */
public interface TicRepository<T extends TicBaseMeasurement> {

  void saveAndFlush(T var1);

  <T> List<T> query(String hql, Class<T> clazz);

}
