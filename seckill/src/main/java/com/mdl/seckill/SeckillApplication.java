package com.mdl.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan
@MapperScan("com.mdl.seckill.dao")
@ComponentScan(basePackages = {"com.mdl.seckill.*"})
public class SeckillApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeckillApplication.class, args);
  }

}
