package com.mdl.influxdb2.config;

import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBMapper;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb2.config
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月14日 22:57
 * ----------------- ----------------- -----------------
 */
public class InfluxMapper extends InfluxDBMapper {

  public InfluxMapper(InfluxDB influxDB) {
    super(influxDB);
  }

}
