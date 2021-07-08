package com.mdl.alkb.server.order;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient//代表自己是一个服务提供方
@EnableFeignClients
public class AppOrderApplication {

  public static void main(String[] args) {

    MDC.put("TRACE_ID", "Application");
    MDC.put("TRACE_PID", "");

    System.setProperty("spring.config.location", "classpath:/application_order.yml");
    SpringApplication.run(AppOrderApplication.class, args);
  }

}
