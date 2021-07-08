package com.mdl.alkb.server.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient//代表自己是一个服务提供方
@EnableFeignClients
public class AppUserCenterApplication {

  public static void main(String[] args) {
    System.setProperty("spring.config.location","classpath:/application_user.yml");
    SpringApplication.run(AppUserCenterApplication.class, args);
  }


}
