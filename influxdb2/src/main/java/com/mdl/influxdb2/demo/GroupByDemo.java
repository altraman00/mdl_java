package com.mdl.influxdb2.demo;

import java.util.logging.Logger;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import static com.mdl.influxdb2.demo.model.Constants.DATABASE;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_PASSWORD;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_URL;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_USERNAME;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gte;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.lte;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.op;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.raw;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.ti;
import static org.influxdb.querybuilder.FunctionFactory.time;
import static org.influxdb.querybuilder.Operations.SUB;
import static org.influxdb.querybuilder.time.DurationLiteral.MINUTE;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb2.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月14日 23:57
 * ----------------- ----------------- -----------------
 */
public class GroupByDemo {

  private static final Logger LOGGER = Logger.getLogger(GroupByDemo.class.getName());

  public static void main(String[] args) {
    InfluxDB influxDB = InfluxDBFactory.connect(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    influxDB.createDatabase(DATABASE);

    groupBySingleTag(influxDB);
    groupByMultipleTags(influxDB);
    groupByAllTags(influxDB);
    groupByTwelveMinuteIntervals(influxDB);
    groupByTwelveMinutesIntervalsAndByTag(influxDB);
    groupByEighteenMinuteIntervalShiftBoundaries(influxDB);
    groupByEighteenMinuteIntervalShiftBoundariesBack(influxDB);
    groupByFill(influxDB);
  }

  private static void groupBySingleTag(InfluxDB influxDB) {
    Query query = select().mean("water_level").from(DATABASE, "h2o_feet").groupBy("location");
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void groupByMultipleTags(InfluxDB influxDB) {
    Query query = select().mean("index").from(DATABASE,"h2o_feet")
        .groupBy("location","randtag");
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void groupByAllTags(InfluxDB influxDB) {
    Query query = select().mean("index").from(DATABASE,"h2o_feet")
        .groupBy(raw("*"));
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void groupByTwelveMinuteIntervals(InfluxDB influxDB) {
    Query query = select().count("water_level").from(DATABASE,"h2o_feet")
        .where(eq("location","coyote_creek"))
        .and(gte("time","2015-08-18T00:00:00Z"))
        .and(lte("time","2015-08-18T00:30:00Z"))
        .groupBy(time(12L,MINUTE));
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void groupByTwelveMinutesIntervalsAndByTag(InfluxDB influxDB) {
    Query query = select().count("water_level").from(DATABASE,"h2o_feet")
        .where()
        .and(gte("time","2015-08-18T00:00:00Z"))
        .and(lte("time","2015-08-18T00:30:00Z"))
        .groupBy(time(12L,MINUTE),"location");
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void groupByEighteenMinuteIntervalShiftBoundaries(InfluxDB influxDB) {
    Query query = select().mean("water_level").from(DATABASE,"h2o_feet")
        .where(eq("location","coyote_creek"))
        .and(gte("time","2015-08-18T00:06:00Z"))
        .and(lte("time","2015-08-18T00:54:00Z"))
        .groupBy(time(18L,MINUTE,6L,MINUTE));
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void groupByEighteenMinuteIntervalShiftBoundariesBack(InfluxDB influxDB) {
    Query query = select().mean("water_level").from(DATABASE,"h2o_feet")
        .where(eq("location","coyote_creek"))
        .and(gte("time","2015-08-18T00:06:00Z"))
        .and(lte("time","2015-08-18T00:54:00Z"))
        .groupBy(time(18L,MINUTE,-12L,MINUTE));
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void groupByFill(InfluxDB influxDB) {
    Query query = select()
        .column("water_level")
        .from(DATABASE, "h2o_feet")
        .where(gt("time", op(ti(24043524l, MINUTE), SUB, ti(6l, MINUTE))))
        .groupBy("water_level")
        .fill(100);
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

}
