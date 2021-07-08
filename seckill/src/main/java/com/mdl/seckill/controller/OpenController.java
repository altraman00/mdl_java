package com.mdl.seckill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年03月06日 17:52
 * ----------------- ----------------- -----------------
 */

@RequestMapping("/open")
@RestController
public class OpenController {

  @GetMapping("/hello")
  public String queryById(String str) {
    return str;
  }

}
