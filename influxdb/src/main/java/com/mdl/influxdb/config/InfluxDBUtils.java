package com.mdl.influxdb.config;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Point.Builder;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Project : influxdb
 * @Package Name : com.mobvoi.influxdb.config
 * @Author : xiekun
 * @Desc : 查询语句参考 https://github.com/influxdata/influxdb-java/blob/master/QUERY_BUILDER.md
 * @Create Date : 2021年06月15日 17:39
 * ----------------- ----------------- -----------------
 */
public class InfluxDBUtils {

  private static final Logger logger = LoggerFactory.getLogger(InfluxDBUtils.class);

  private String url;
  private String userName;
  private String password;
  private String database;
  //数据保存策略
  private String retentionPolicy;
  /**
   * InfluxDB实例
   */
  private InfluxDB influxDB;


  InfluxDBUtils(String url, String userName, String password, String database,
      String retentionPolicy) {
    logger.info("Initializing connection with influxdb http://" + url + "?dbname=" + database);
    this.userName = userName;
    this.password = password;
    this.url = url;
    this.database = database;
    this.retentionPolicy =
        retentionPolicy == null || "".equals(retentionPolicy) ? "autogen" : retentionPolicy;
    this.influxDB = influxDbBuild();
  }

  /**
   * 连接数据库 ，若不存在则创建
   *
   * @return influxDb实例
   */
  private InfluxDB influxDbBuild() {
    if (influxDB == null) {
      influxDB = InfluxDBFactory.connect(url, userName, password);
    }
    try {
      createDB(database);
      influxDB.setDatabase(database);
    } catch (Exception e) {
      logger.error("create controller db failed, error: {}", e.getMessage());
    } finally {
      influxDB.setRetentionPolicy(retentionPolicy);
    }
    influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
    return influxDB;
  }

  /****
   *  创建数据库
   * @param database
   */
  private void createDB(String database) {
    influxDB.query(new Query("CREATE DATABASE " + database));
  }


  /**
   * 测试连接是否正常
   *
   * @return true 正常
   */
  public boolean ping() {
    boolean isConnected = false;
    Pong pong;
    try {
      pong = influxDB.ping();
      if (pong != null) {
        isConnected = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return isConnected;
  }

  /**
   * 创建自定义保留策略
   *
   * @param policyName 策略名
   * @param duration 保存天数
   * @param replication 保存副本数量
   * @param isDefault 是否设为默认保留策略
   */
  public void createRetentionPolicy(String policyName, String duration, int replication,
      Boolean isDefault) {
    String sql = String
        .format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s ", policyName,
            database, duration, replication);
    if (isDefault) {
      sql = sql + " DEFAULT";
    }
    this.query(sql);
  }

  /**
   * 创建默认的保留策略
   *
   * RetentionPolicy：default，保存天数：30天，保存副本数量：1 设为默认保留策略
   */
  public void createDefaultRetentionPolicy() {
    String command = String
        .format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s DEFAULT",
            "default", database, "30d", 1);
    this.query(command);
  }

  /**
   * 查询
   *
   * @param command 查询语句
   */
  public QueryResult query(String command) {
    return influxDB.query(new Query(command, database));
  }


  /**
   * 插入
   *
   * @param measurement 表
   * @param tags 标签
   * @param fields 字段
   */
  public void write(String measurement, Map<String, String> tags, Map<String, Object> fields,
      long time, TimeUnit timeUnit) {
    Builder builder = Point.measurement(measurement);
    builder.tag(tags);
    builder.fields(fields);
    if (0 != time) {
      builder.time(time, timeUnit);
    }
    influxDB.write(database, retentionPolicy, builder.build());
  }


  public void write(Point point) {
    BatchPoints batchPoints = BatchPoints
        .database(database)
        .consistency(InfluxDB.ConsistencyLevel.ALL)
        .build();
    logger.info("Write metrics:: point: " + point);
    batchPoints.point(point);
    influxDB.write(batchPoints);
  }

  public void write(String measurement, Map<String, String> tags, Map<String, Object> fields) {
    BatchPoints batchPoints = BatchPoints
        .database(database)
        .consistency(InfluxDB.ConsistencyLevel.ALL)
        .build();
    Point point = Point.measurement(measurement)
        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .tag(tags)
        .fields(fields)
        .build();
    logger.info("Write metrics:: tags: " + getTags(tags) + " fields: " + getFields(fields));
    batchPoints.point(point);
    influxDB.write(batchPoints);
  }


  public String getTags(Map<String, String> tags) {
    String tags_ = "";
    Set set = tags.entrySet();
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
      Map.Entry mentry = (Map.Entry) iterator.next();
      tags_ = tags_ + " " + mentry.getKey() + ": " + mentry.getValue();
    }
    return tags_;
  }

  public String getFields(Map<String, Object> fields) {
    String fields_ = "";
    Set set = fields.entrySet();
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
      Map.Entry mentry = (Map.Entry) iterator.next();
      fields_ = fields_ + " " + mentry.getKey() + ": " + mentry.getValue();
    }
    return fields_;
  }

  public Query query(String database, String table) {
    Query query = select().from(database, table);
    return query;
  }


}
