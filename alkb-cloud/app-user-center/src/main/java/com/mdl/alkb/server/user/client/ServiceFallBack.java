package com.mdl.alkb.server.user.client;

import org.springframework.stereotype.Component;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.user.client
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月03日 16:35
 * ----------------- ----------------- -----------------
 */

@Component
public class ServiceFallBack implements OrderServiceFeignClient {

  @Override
  public String getUserOrderByUserId(String userId) {
    return "订单服务调用失败";
  }



}
