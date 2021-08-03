package com.mdl.influxdb4.config;

import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBMapper;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb3.config
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月29日 22:01
 * ----------------- ----------------- -----------------
 */
public class TicInfluxDBMapper<T> extends InfluxDBMapper {

  public TicInfluxDBMapper(InfluxDB influxDB) {
    super(influxDB);
  }

  public void saveAll(Iterable<T> itr){
    itr.forEach(t -> save(t));
  }

}
