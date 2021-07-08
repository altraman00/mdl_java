package com.mdl.influxdb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project : influxdb
 * @Package Name : com.mobvoi.influxdb.config
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年06月15日 17:38
 * ----------------- ----------------- -----------------
 */

@Configuration
public class InfluxDbConfig {

  @Value("${spring.influx.url:''}")
  private String influxDBUrl;

  @Value("${spring.influx.user:''}")
  private String userName;

  @Value("${spring.influx.password:''}")
  private String password;

  @Value("${spring.influx.database:''}")
  private String database;

  @Value("${spring.influx.retentionPolicy:''}")
  private String retentionPolicy;

  @Bean
  public InfluxDBUtils influxDbUtils() {
    return new InfluxDBUtils(influxDBUrl,userName, password, database, retentionPolicy);
  }

}
