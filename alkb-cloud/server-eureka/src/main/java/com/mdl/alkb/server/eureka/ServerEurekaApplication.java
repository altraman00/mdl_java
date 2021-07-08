package com.mdl.alkb.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //当前使用eureka的server
public class ServerEurekaApplication {

  public static void main(String[] args) {
    System.setProperty("spring.config.location","classpath:/application_eureka.yml");
    SpringApplication.run(ServerEurekaApplication.class, args);
  }

}
