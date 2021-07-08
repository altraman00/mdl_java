//package com.mdl.influxdb.controller;
//
//import java.util.concurrent.TimeUnit;
//import javax.annotation.Resource;
//import org.influxdb.InfluxDB;
//import org.influxdb.dto.Point;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Project : influxdb
// * @Package Name : com.mobvoi.influxdb.controller
// * @Author : xiekun
// * @Desc :
// * @Create Date : 2021年06月15日 16:09
// * ----------------- ----------------- -----------------
// */
//
//@RestController
//public class InfluxdbController {
//
//  private static final String DB_NAME = "tichealth";
//
//  private static final String MEASURMENT_NAME = "disk_free";
//
//  /**
//   * 注入influxDB
//   */
//  @Resource
//  private InfluxDB influxDB;
//
//  @PostMapping("writePoint")
//  public String insertPoint() {
//    try {
//      influxDB.setDatabase(DB_NAME);
//      //新建一个Point,指定表名，和tag以及field
//      //由于是链式调用可以增加多个Tag和Field
//      Point point = Point.measurement(MEASURMENT_NAME)
//          .tag("account_id", "123456789")
//          .tag("type", "heart_rate")
//          .addField("value", 99.0)
//          .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//          .build();
//      influxDB.write(point);
//    } catch (Exception e) {
//      e.printStackTrace();
//      return "error";
//    }
//    return "success";
//  }
//
//
//}
