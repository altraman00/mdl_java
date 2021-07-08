package com.mdl.proto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ProtoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProtoApplication.class, args);
  }


  @Configuration
  public static class RestClientConfiguration {

//    @Bean
//    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
//      return new RestTemplate(Arrays.asList(hmc));
//    }
//
//    @Bean
//    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
//      return new ProtobufHttpMessageConverter();
//    }


  }


//  @Bean
//  ProtobufHttpMessageConverter protobufHttpMessageConverter() {
//    return new ProtobufHttpMessageConverter();
//  }

//  @Bean
//  ProtobufJsonFormatHttpMessageConverter protobufJsonFormatHttpMessageConverter() {
//    return new ProtobufJsonFormatHttpMessageConverter();
//  }

//  @Bean
//  RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
//    return new RestTemplate(Arrays.asList(hmc));
//  }


}
