package com.mdl.influxdb2.demo;

import static com.mdl.influxdb2.demo.model.Constants.DATABASE;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_PASSWORD;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_URL;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_USERNAME;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gte;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.lte;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.FunctionFactory.time;
import static org.influxdb.querybuilder.time.DurationLiteral.MINUTE;

import java.util.logging.Logger;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.querybuilder.RawText;

public class IntoDemo {

  private static final Logger LOGGER = Logger.getLogger(IntoDemo.class.getName());

  public static void main(String[] args) {

    InfluxDB influxDB = InfluxDBFactory.connect(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    influxDB.createDatabase(DATABASE);

//    renameDatabase(influxDB);

//    writeResultsToMeasurement(influxDB);
    writeAggregatedToMeasurement(influxDB);
  }


  /**
   * 复制表
   * @param influxDB
   */
  private static void writeResultsToMeasurement(InfluxDB influxDB) {
    Query query = select().column("water_level")
//        .into("h2o_feet_copy_1")
        .from(DATABASE,"h2o_feet")
        .where(eq("location","coyote_creek"));
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    System.out.println(queryResult);
  }

  private static void writeAggregatedToMeasurement(InfluxDB influxDB) {
    long starttime = 1566000000000000000L;
    long endtime = 1566003240000000000L;
    Query query = select()
        .mean("water_level")
        .into("all_my_averages")
        .from(DATABASE,"h2o_feet")
        .where(eq("location","coyote_creek"))
        .and(gte("time",starttime))
        .and(lte("time",endtime))
        .groupBy(time(12L,MINUTE));
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    System.out.println(queryResult);
  }

  /**
   * 复制数据库（执行没成功）
   * @param influxDB
   */
  private static void renameDatabase(InfluxDB influxDB) {
    Query query = select()
        .into("\"copy_NOAA_water_database\".\"autogen\".:MEASUREMENT")
        .from(DATABASE, "\"NOAA_water_database\".\"autogen\"./.*/")
        .groupBy(new RawText("*"));
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    System.out.println(queryResult);
  }



  //LOGGER.info("Executing query "+query.getCommand());

}
