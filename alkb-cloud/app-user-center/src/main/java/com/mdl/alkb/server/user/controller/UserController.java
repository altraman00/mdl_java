package com.mdl.alkb.server.user.controller;

import com.mdl.alkb.server.user.client.OrderServiceFeignClient;
import com.mdl.alkb.server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Project : cloudService
 * @Package Name : com.mdl.alkb.fn.repository
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月31日 14:50
 * ----------------- ----------------- -----------------
 */

@Api(value = "UserController", tags = "用户相关")
@RestController
@RequestMapping("user")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  /**
   * 需要添加RestTemplateConfig文件注入Restemplate
   **/
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private OrderServiceFeignClient orderServiceFeignClient;


  @ApiOperation(value = "根据id查询")
  @GetMapping("/{userId}")
  public String getUserById(@PathVariable("userId") String userId) {
    return userService.findById(userId);
  }

  @ApiOperation(value = "第一种调用方式")
  @GetMapping("/us1")
  public String getUserOrderByUserId(String userId) {
    String forObject = new RestTemplate()
        .getForObject("http://localhost:8081/user/" + userId + "/order", String.class);

    return forObject;
  }

  @ApiOperation(value = "第二种调用方式")
  @GetMapping("/us2")
  public String getUserOrderByUserId2(String userId) {
    //根据服务名 获取服务列表 根据算法选取某个服务 并访问某个服务的网络位置
    ServiceInstance serviceInstance = loadBalancerClient.choose("APP-ORDER-SERVICE");
    String forObject = new RestTemplate()
        .getForObject(
            "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/"
                + userId + "/order",
            String.class);

    return forObject;
  }

  @ApiOperation(value = "第三种调用方式")
  @GetMapping("/us3")
  public String getUserOrderByUserId3(String userId) {
    //用到restTemplate注入的方式
    ServiceInstance serviceInstance = loadBalancerClient.choose("APP-ORDER-SERVICE");
    String forObject = restTemplate.getForObject(
        "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + userId
            + "/order",
        String.class);

    return forObject;
  }

  @ApiOperation(value = "第四种调用方式")
  @GetMapping("/us4")
  public String getUserOrderByUserId4(String userId) {
    logger.info("getUserOrderByUserId4,userId:{}",userId);
    //使用feignClient的方式
    return orderServiceFeignClient.getUserOrderByUserId(userId);
  }


}
