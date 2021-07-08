package com.mdl.alkb.server.order.controller;

import com.mdl.alkb.server.order.service.BiDefOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : cloudService
 * @Package Name : com.mdl.alkb.fn.repository
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月31日 14:50
 * ----------------- ----------------- -----------------
 */

@Api(value = "OrderController", tags = "订单相关")
@RestController
@RequestMapping("order")
public class BiDefOrderController {

  @Autowired
  private BiDefOrderService orderService;

  @ApiOperation(value = "根据id查询")
  @GetMapping("/id")
  public String bridgeInfo(String id) {
    return orderService.findById(id);

  }

}
