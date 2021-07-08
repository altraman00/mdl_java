package com.mdl.hbase.module.controller;

import com.mdl.hbase.module.domain.HealthEntity;
import com.mdl.hbase.module.service.HealthService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.module
 * @Author : xiekun
 * @Create Date : 2020年11月23日 17:30
 * ----------------- ----------------- -----------------
 */
@RestController
@RequestMapping("/health")
public class HealthController {

  @Autowired
  private HealthService healthService;

  @GetMapping("/hello")
  public String hello(String str) {
    return str;
  }

  @PostMapping("/save")
  public HealthEntity save(@RequestBody HealthEntity health) {
    healthService.saveOrUpdate("health_d", health);
    return health;
  }

  @GetMapping("/get")
  public Map<String, Object> query(String tableName, String row) {
    return healthService.query(tableName, row);
  }

  @GetMapping("/get_health")
  public HealthEntity getHealth(String tableName, String row) {
    return healthService.queryHealth(tableName, row);
  }

}
