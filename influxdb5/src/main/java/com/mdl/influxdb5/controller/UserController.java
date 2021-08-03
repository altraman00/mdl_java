package com.mdl.influxdb5.controller;

import com.mdl.influxdb5.dao.UserTestDao;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 17:11
 * ----------------- ----------------- -----------------
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource
  private UserTestDao userTestDao;

  @GetMapping("/byId")
  public String queryByIdTest(Long id) {
    System.out.println("userTestDao:" + userTestDao);
    String byId = userTestDao.getById(id);
    return byId;
  }

}
