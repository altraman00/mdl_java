package com.mdl.influxdb4.repository.base;

import com.mdl.influxdb4.measurements.TicBaseMeasurement;
import com.mdl.influxdb4.config.TicInfluxDBMapper;
import java.util.List;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.base.modules.influxdb.service
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月29日 16:04
 * ----------------- ----------------- -----------------
 */
public abstract class AbstractInfluxdbRepository<T extends TicBaseMeasurement> implements InfluxdbRepository<T> {

  @Autowired
  private TicInfluxDBMapper ticInfluxDBMapper;

  @Override
  public void saveAndFlush(TicBaseMeasurement var1) {
    ticInfluxDBMapper.save(var1);
  }

  @Override
  public void saveAll(Iterable var1) {
    ticInfluxDBMapper.saveAll(var1);
  }

  @Override
  public List<T> query(String hql, Class clazz) {
    String database = this.getDatabaseName(clazz);
    Query query = new Query(hql, database);
    return ticInfluxDBMapper.query(query, clazz);
  }

//  private void getBase(Class clazz) {
//    Class<?> modelType = clazz.getClass();
//    String database = this.getDatabaseName(modelType);
//    String measurement = this.getMeasurementName(modelType);
//  }
//
//  String getMeasurementName(Class<?> clazz) {
//    return (clazz.getAnnotation(Measurement.class)).name();
//  }

  String getDatabaseName(Class<?> clazz) {
    return (clazz.getAnnotation(Measurement.class)).database();
  }

}
