package com.mdl.influxdb.repository.influxdb;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.asc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.cop;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gte;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.lt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.lte;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.neq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.op;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.raw;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.subTime;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.ti;
import static org.influxdb.querybuilder.FunctionFactory.time;
import static org.influxdb.querybuilder.Operations.MUL;
import static org.influxdb.querybuilder.Operations.SUB;
import static org.influxdb.querybuilder.time.DurationLiteral.DAY;
import static org.influxdb.querybuilder.time.DurationLiteral.MINUTE;

import com.mdl.influxdb.config.InfluxDBUtils;
import com.mdl.influxdb.model.HealthPoint;
import java.util.List;
import javax.annotation.Resource;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb.config
 * @Author : xiekun
 * @Desc : 参考：https://blog.csdn.net/danpu0978/article/details/107274537?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242
 * 参考：https://blog.csdn.net/danpu0978/article/details/106766643?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control
 * 参考：https://blog.csdn.net/danpu0978/article/details/106766465
 * @Create Date : 2021年07月15日 21:03
 * ----------------- ----------------- -----------------
 */

@Component
public class TicHealthRepository {

  public Logger logger = LoggerFactory.getLogger(TicHealthRepository.class);

  private static final String measurement = "health_point";
  
  @Value("${spring.influx.database:''}")
  private String database;

  @Value("${spring.influx.retentionPolicy:'autogen'}")
  private String retentionPolicy;

  @Resource
  private InfluxDBMapper influxDBMapper;

  @Resource
  private InfluxDB influxDB;

  @Autowired
  private InfluxDBUtils influxDBUtils;


  public void writeOne(Object obj) {
    influxDBUtils.writeOne(obj);
  }

  public void writeBatchByList(List<?> list) {
    influxDBUtils.writeBatchByList(list);
  }

  public List<HealthPoint> selectAll() {
    Query query = select().from(database, measurement);
    return influxDBMapper.query(query, HealthPoint.class);
  }

  public List<HealthPoint> selectGreater() {
    Query query = select().from(database, measurement).where(gt("f_level", 8));
    logger.info("Executing query " + query.getCommand());
    return influxDBMapper.query(query, HealthPoint.class);
  }

  public QueryResult selectWithOperation() {
    Query query = select().op(op(cop("f_level", MUL, 2), "+", 4)).from(database, measurement);
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public List<HealthPoint> selectByStringFieldKeyValue() {
    Query query = select().from(database, measurement).where(eq("t_data_source_name", "santa_monica"));
    logger.info("Executing query " + query.getCommand());
    return influxDBMapper.query(query, HealthPoint.class);
  }

  public List<HealthPoint> selectByTagAndFieldValues() {
    Query query = select().column("f_level").from(database, measurement)
        .where(neq("t_data_source_name", "santa_monica"))
        .andNested()
        .and(lt("f_level", -0.59))
        .or(gt("f_level", 9.95))
        .close();
    logger.info("Executing query " + query.getCommand());
    return influxDBMapper.query(query, HealthPoint.class);
  }

  public List<HealthPoint> selectByTimestamps() {
    Query query = select().from(database, measurement)
        .where(gt("time", subTime(7, DAY)));
    logger.info("Executing query " + query.getCommand());
    return influxDBMapper.query(query, HealthPoint.class);
  }

  public List<HealthPoint> selectFields() {
    Query selectFields = select("level description", "t_data_source_name").from(database, measurement);
    return influxDBMapper.query(selectFields, HealthPoint.class);

  }

  public QueryResult groupBySingleTag() {
    Query query = select().mean("f_level").from(database, measurement).groupBy("t_data_source_name");
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult groupByMultipleTags() {
    Query query = select().mean("index").from(database, measurement).groupBy("t_data_source_name", "randtag");
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult groupByAllTags() {
    Query query = select().mean("index").from(database, measurement).groupBy(raw("*"));
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult groupByTwelveMinuteIntervals() {
    Query query = select().count("f_level").from(database, measurement)
        .where(eq("t_data_source_name", "coyote_creek"))
        .and(gte("time", "2021-07-15T21:00:00Z"))
        .and(lte("time", "2021-07-15T23:30:00Z"))
        .groupBy(time(12L, MINUTE));
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult groupByTwelveMinutesIntervalsAndByTag() {
    Query query = select().count("f_level").from(database, measurement)
        .where()
        .and(gte("time", "2021-07-15T21:00:00Z"))
        .and(lte("time", "2021-07-15T23:30:00Z"))
        .groupBy(time(12L, MINUTE), "t_data_source_name");
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult groupByEighteenMinuteIntervalShiftBoundaries() {
    Query query = select().mean("f_level").from(database, measurement)
        .where(eq("t_data_source_name", "coyote_creek"))
        .and(gte("time", "2021-07-15T21:06:00Z"))
        .and(lte("time", "2021-07-15T21:54:00Z"))
        .groupBy(time(18L, MINUTE, 6L, MINUTE));
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult groupByEighteenMinuteIntervalShiftBoundariesBack() {
    Query query = select().mean("f_level").from(database, measurement)
        .where(eq("t_data_source_name", "coyote_creek"))
        .and(gte("time", "2021-07-15T21:06:00Z"))
        .and(lte("time", "2021-07-15T21:54:00Z"))
        .groupBy(time(18L, MINUTE, -12L, MINUTE));
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult groupByFill() {
    Query query = select()
        .column("f_level")
        .from(database, measurement)
        .where(gt("time", op(ti(24043524L, MINUTE), SUB, ti(6L, MINUTE))))
        .groupBy("f_level")
        .fill(100);
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult writeAggregatedToMeasurement() {
    Query query = select()
        .mean("f_level")
        .into("all_my_averages")
        .from(database, measurement)
        .where(eq("t_data_source_name", "coyote_creek"))
        .and(gte("time", "2021-07-15T21:00:00Z"))
        .and(lte("time", "2021-07-15T23:30:00Z"))
        .groupBy(time(12L, MINUTE));
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }


  public QueryResult orderAscending() {
    Query query = select().from(database, measurement)
        .where(eq("t_data_source_name", "santa_monica"))
        .orderBy(asc());
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }

  public QueryResult orderDescending() {
    Query query = select().from(database, measurement)
        .where(eq("t_data_source_name", "santa_monica"))
        .orderBy(desc());
    logger.info("Executing query " + query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    return queryResult;
  }


}
