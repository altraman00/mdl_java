package com.mdl.alkb.server.order.controller;

import com.mdl.alkb.server.order.client.UserServiceFeignClient;
import com.mdl.alkb.server.order.entity.BiUserOrderEntity;
import com.mdl.alkb.server.order.service.BiUserOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.order.controller
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月01日 16:33
 * ----------------- ----------------- -----------------
 */

@Api(value = "UserOrderController", tags = "用户订单相关")
@RestController
@RequestMapping("/order")
public class BiUserOrderController {

  private static final Logger logger = LoggerFactory.getLogger(BiUserOrderController.class);

  @Value("${server.port}")
  private String port;

  @Autowired
  private BiUserOrderService userOrderService;

  @Autowired
  private UserServiceFeignClient userServiceFeignClient;

  @ApiOperation(value = "根据用户id查询")
  @GetMapping("/user/{userId}")
  public List<BiUserOrderEntity> getOrderListByUserId(@PathVariable("userId") String userId) {
    logger.info("getOrderListByUserId,userId:{}", userId);
    List<BiUserOrderEntity> byUserId = userOrderService.findByUserId(userId);
    System.out.println("server.port:" + port);
    byUserId.stream().forEach(t -> t.setName(t.getName() + "port:" + port));
    return byUserId;
  }

  @ApiOperation(value = "根据订单id查询用户的所有订单")
  @GetMapping("/{orderId}")
  public String getOrderListByOrderId(@PathVariable("orderId") String orderId) {
    logger.info("getOrderListByOrderId,orderId:{}", orderId);
    String userById = "";
    Optional<BiUserOrderEntity> byId = Optional.ofNullable(userOrderService.findById(orderId));
    if (byId.isPresent()) {
      userById = userServiceFeignClient.getUserById(byId.get().getUserId());
    }
    return userById;
  }

}
