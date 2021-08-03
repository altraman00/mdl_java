package com.mdl.influxdb2.demo;

import static com.mdl.influxdb2.demo.model.Constants.DATABASE_PASSWORD;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_URL;
import static com.mdl.influxdb2.demo.model.Constants.DATABASE_USERNAME;

import com.mdl.influxdb2.common.JSONUtils;
import com.mdl.influxdb2.demo.model.H2OFeetMeasurement;
import java.util.List;
import java.util.logging.Logger;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBMapper;


/**
 * 参考：https://github.com/gkatzioura/influxb-mapper-showcase/blob/master/src/main/java/com/gkatzioura/mapper/showcase/ShowCaseConstants.java
 *
 * 官方测试数据地址：
 * https://s3.amazonaws.com/noaa.water-database/NOAA_data.txt
 *
 */
public class InfluxDBMapperDemo {

  private static final Logger logger = Logger.getLogger(InfluxDBMapperDemo.class.getName());

  public static void main(String[] args) throws InterruptedException {

    InfluxDB influxDB = InfluxDBFactory.connect(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//    influxDB.createDatabase(DATABASE);

    InfluxDBMapper influxDBMapper = new InfluxDBMapper(influxDB);
    List<H2OFeetMeasurement> h2OFeetMeasurements = influxDBMapper.query(H2OFeetMeasurement.class);

    logger.info("test the measurement" + h2OFeetMeasurements);

    for (int i = 0; i < 10; i++) {
//      Thread.sleep(1000);
      H2OFeetMeasurement h2OFeetMeasurement = new H2OFeetMeasurement();
//      h2OFeetMeasurement.setTime(Instant.now());
//      h2OFeetMeasurement.setTime(System.currentTimeMillis());
      h2OFeetMeasurement.setLevelDescription("Just a test" + i);
      h2OFeetMeasurement.setLocation("London10");
      h2OFeetMeasurement.setWaterLevel(1.7d * i);
      influxDBMapper.save(h2OFeetMeasurement);
    }

//    List<H2OFeetMeasurement> measurements = influxDBMapper.query(H2OFeetMeasurement.class);
//
//    logger.info(JSONUtils.objectToJson(measurements));
//
//    H2OFeetMeasurement h2OFeetMeasurement1 = measurements.get(measurements.size() - 1);
//    assert h2OFeetMeasurement1.getLevelDescription().equals("Just a test");
  }

}
