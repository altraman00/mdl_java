package com.mdl.influxdb3.repository.base;


import com.mdl.influxdb3.measurements.TicBaseMeasurement;
import java.util.List;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.base.modules.influxdb.service
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月29日 15:40
 * ----------------- ----------------- -----------------
 */
public interface InfluxdbRepository<T extends TicBaseMeasurement> {

  void saveAndFlush(T var1);

  void saveAll(Iterable<T> var1);

  <T> List<T> query(String hql, Class<T> clazz);

//  List<T> findAllById(Iterable<ID> var1);

}
