package com.mdl.influxdb2.config;

import lombok.extern.log4j.Log4j2;
import org.influxdb.InfluxDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb2.config
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月14日 22:58
 * ----------------- ----------------- -----------------
 */

@Log4j2
@Configuration
public class InfluxAutoConfiguration {

  @Bean
  public InfluxMapper influxMapper(InfluxDB influxDB) {
    InfluxMapper influxMapper = new InfluxMapper(influxDB);
    return influxMapper;
  }

}
