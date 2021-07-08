package com.mdl.alkb.server.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.user.config
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月02日 10:57
 * ----------------- ----------------- -----------------
 */

@Configuration
public class RestTemplateConfig {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
