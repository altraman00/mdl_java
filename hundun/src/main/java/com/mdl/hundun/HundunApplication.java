package com.mdl.hundun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan
@MapperScan("com.mdl.hundun.mapper")
@ComponentScan(basePackages = {"com.mdl.hundun.*"})
public class HundunApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.location","classpath:/application.properties");
        SpringApplication.run(HundunApplication.class, args);
    }

}
