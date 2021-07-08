//package com.mobvoi.influxdb.controller;
//
//import com.influxdb.annotations.Column;
//import com.influxdb.annotations.Measurement;
//import com.influxdb.client.InfluxDBClient;
//import com.influxdb.client.InfluxDBClientFactory;
//import com.influxdb.client.WriteApi;
//import com.influxdb.client.domain.WritePrecision;
//import com.influxdb.client.write.Point;
//import com.influxdb.query.FluxTable;
//import java.time.Instant;
//import java.util.List;
//
///**
// * @Project : influxdb
// * @Package Name : com.mobvoi.influxdb
// * @Author : xiekun
// * @Desc :
// * @Create Date : 2021年06月11日 18:12
// * ----------------- ----------------- -----------------
// */
//
//
//public class InfluxDB2Example {
//
//  public static void main(final String[] args) {
//
//    // You can generate a Token from the "Tokens Tab" in the UI
//    String token = "P0MmazAEkoASqpKoSYWnTvmYwGtQxwdIAHEH5V1qHOEUS4CslzppSkRuqow5mPZ-ljj9TxutOFcCKk8ZwRV_OA==";
//    String bucket = "tichealth";
//    String org = "mobvoi";
//
//    InfluxDBClient client = InfluxDBClientFactory
//        .create("http://10.27.0.47:8086", token.toCharArray());
//
//    //Use InfluxDB Line Protocol to write data
//    String data = "mem,host=host1 used_percent=23.43234543";
//    try (WriteApi writeApi = client.getWriteApi()) {
//      writeApi.writeRecord(bucket, org, WritePrecision.NS, data);
//    }
//
//    //Use a Data Point to write data
//    Point point = Point
//        .measurement("mem")
//        .addTag("host", "host1")
//        .addField("used_percent", 23.43234543)
//        .time(Instant.now(), WritePrecision.NS);
//
//    try (WriteApi writeApi = client.getWriteApi()) {
//      writeApi.writePoint(bucket, org, point);
//    }
//
//    //Use POJO and corresponding class to write data
//    Mem mem = new Mem();
//    mem.host = "host1";
//    mem.used_percent = 23.43234543;
//    mem.time = Instant.now();
//
//    try (WriteApi writeApi = client.getWriteApi()) {
//      writeApi.writeMeasurement(bucket, org, WritePrecision.NS, mem);
//    }
//
//    String query = String.format("from(bucket: \"%s\") |> range(start: -1h)", bucket);
//    List<FluxTable> tables = client.getQueryApi().query(query, org);
//
//    System.out.println(tables);
//
//  }
//
//
//  @Measurement(name = "mem")
//  public static class Mem {
//
//    @Column(tag = true)
//    String host;
//    @Column
//    Double used_percent;
//    @Column(timestamp = true)
//    Instant time;
//  }
//
//
//}