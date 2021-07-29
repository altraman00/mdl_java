package com.mdl.influxdb.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project : influxdb
 * @Package Name : com.mdl.influxdb.config
 * @Author : xiekun
 * @Desc : influxdb基础配置，也可以直接使用InfluxDB，这么做是为了配置更多的默认参数
 * @Create Date : 2021年06月15日 17:38
 * ----------------- ----------------- -----------------
 */

@Configuration
public class InfluxDbConfig {

  private static final Logger logger = LoggerFactory.getLogger(InfluxDbConfig.class);


  @Value("${spring.influx.url:''}")
  private String influxDBUrl;

  @Value("${spring.influx.user:''}")
  private String userName;

  @Value("${spring.influx.password:''}")
  private String password;

  @Value("${spring.influx.database:''}")
  private String database;

  @Value("${spring.influx.retentionPolicy:autogen}")
  private String retentionPolicy;

  private static InfluxDB influxDB = null;

  @Bean
  public InfluxDB influxDbUtils() {
    if (influxDB == null) {
      logger.info("Initializing connection with influxdb http://" + influxDBUrl + "?dbname=" + database);
      influxDB = InfluxDBFactory.connect(influxDBUrl, userName, password);
      try {
        createDB(influxDB, database);
        influxDB.setDatabase(database);
      } catch (Exception e) {
        logger.error("create controller db failed", e);
      } finally {
        //设置默认策略
        influxDB.setRetentionPolicy(retentionPolicy);
      }
      //设置日志输出级别
      influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
    }
    return influxDB;
  }

  @Bean
  public InfluxDBMapper initInfluxDBMapper() {
    logger.info("Initializing influxDBMapper");
    InfluxDB influxDB = influxDbUtils();
    return new InfluxDBMapper(influxDB);
  }

  /****
   *  创建数据库
   * @param database
   */
  private void createDB(InfluxDB influxDB, String database) {
    influxDB.query(new Query("CREATE DATABASE " + database));
  }


}
