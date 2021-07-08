package com.mdl.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.mdl.file.shardupload.mapper")
@ComponentScan(basePackages = {"com.mdl.file.*"})
public class MaxFileUploadApplication {

  public static void main(String[] args) {
    SpringApplication.run(MaxFileUploadApplication.class, args);
  }

}
