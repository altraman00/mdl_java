package com.mdl.home.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Project : home
 * @Package Name : com.mdl.home.config
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2020年12月31日 10:49
 * ----------------- ----------------- -----------------
 */

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/");
  }

}
