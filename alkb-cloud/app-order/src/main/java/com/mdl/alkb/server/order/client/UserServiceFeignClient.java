package com.mdl.alkb.server.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.order.client
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月02日 18:27
 * ----------------- ----------------- -----------------
 */

@Component
@FeignClient(value = "APP-USER-SERVICE")
public interface UserServiceFeignClient {

  @RequestMapping("/user/{userId}")
  String getUserById(@PathVariable("userId") String userId);

}
