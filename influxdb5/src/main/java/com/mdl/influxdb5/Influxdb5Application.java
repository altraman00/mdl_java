package com.mdl.influxdb5;

import com.mdl.influxdb5.annotations.MyDaoScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MyDaoScan(basePackage = "com.mdl.influxdb5.dao")
public class Influxdb5Application {

  public static void main(String[] args) {
    SpringApplication.run(Influxdb5Application.class, args);
  }

}
