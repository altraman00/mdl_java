package com.mdl.hbase.module.service;

import com.mdl.hbase.module.domain.HealthEntity;
import java.util.List;
import java.util.Map;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.module.service
 * @Author : xiekun
 * @Create Date : 2020年11月23日 17:31
 * ----------------- ----------------- -----------------
 */
public interface HealthService {

  List<Map<String, Object>> query(String tableName, String startRow, String stopRow);

  Map<String, Object> query(String tableName, String row);

  void saveOrUpdate(String tableName, HealthEntity health);

//  void saveOrUpdate(String tableName, List<HealthEntity> healths);

  HealthEntity queryHealth(String tableName, String row);

}
