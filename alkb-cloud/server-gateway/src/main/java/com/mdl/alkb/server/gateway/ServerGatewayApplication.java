package com.mdl.alkb.server.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ServerGatewayApplication {

  public static void main(String[] args) {
//    System.setProperty("spring.config.location","classpath:/application_gateway.yml");
    SpringApplication.run(ServerGatewayApplication.class, args);
  }

}
