package com.mdl.hbase.module;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.module
 * @Author : xiekun
 * @Create Date : 2020年11月23日 18:31
 * ----------------- ----------------- -----------------
 */


@RequestMapping("/open")
@RestController
public class OpenController {

  @GetMapping("/hello")
  public String hello(String str) {
    return str;
  }

}
