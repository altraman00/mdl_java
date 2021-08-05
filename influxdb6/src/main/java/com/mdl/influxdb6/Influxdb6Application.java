package com.mdl.influxdb6;

import com.mdl.influxdb6.annotations.MyDaoScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MyDaoScan(basePackage = "com.mdl.influxdb6.dao")
public class Influxdb6Application {

  public static void main(String[] args) {
    SpringApplication.run(Influxdb6Application.class, args);
  }

}
