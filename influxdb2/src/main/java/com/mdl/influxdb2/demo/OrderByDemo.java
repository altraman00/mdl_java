package com.mdl.influxdb2.demo;

import static com.mdl.influxdb2.demo.model.Constants.DATABASE;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_PASSWORD;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_URL;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_USERNAME;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.asc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;

import java.util.logging.Logger;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class OrderByDemo {

  private static final Logger LOGGER = Logger.getLogger(IntoDemo.class.getName());

  public static void main(String[] args) {

    InfluxDB influxDB = InfluxDBFactory.connect(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//    influxDB.createDatabase(DATABASE);

//    orderAscending(influxDB);
//    orderDescending(influxDB);
    testDB(influxDB);
  }

  private static void testDB(InfluxDB influxDB) {
    Query query = select().from("ticwatch", "disk_free")
        .where(eq("hostname","server01"))
        .orderBy(desc());
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
    System.out.println(queryResult);
  }


  private static void orderAscending(InfluxDB influxDB) {
    Query query = select().from(DATABASE, "h2o_feet")
        .where(eq("location","santa_monica"))
        .orderBy(asc());
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }

  private static void orderDescending(InfluxDB influxDB) {
    Query query = select().from(DATABASE, "h2o_feet")
        .where(eq("location","santa_monica"))
        .orderBy(desc());
    LOGGER.info("Executing query "+query.getCommand());
    QueryResult queryResult = influxDB.query(query);
  }





}
