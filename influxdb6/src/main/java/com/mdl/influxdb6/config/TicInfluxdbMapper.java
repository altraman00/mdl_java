package com.mdl.influxdb6.config;

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
public class TicInfluxdbMapper extends InfluxDBMapper {

  public TicInfluxdbMapper(InfluxDB influxDB) {
    super(influxDB);
  }

}
