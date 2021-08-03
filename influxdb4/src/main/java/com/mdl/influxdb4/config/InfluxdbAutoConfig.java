package com.mdl.influxdb4.config;

import lombok.extern.log4j.Log4j2;
import org.influxdb.InfluxDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb3.config
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月29日 21:59
 * ----------------- ----------------- -----------------
 */
@Log4j2
@Configuration
public class InfluxdbAutoConfig {

  @Bean
  public TicInfluxDBMapper initTicInfluxMapper(InfluxDB influxDB) {
    return new TicInfluxDBMapper(influxDB);
  }

}
