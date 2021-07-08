package com.mdl.hbase.module.service.impl;

import com.mdl.hbase.config.ObjUtils;
import com.mdl.hbase.module.dao.DefaultMapper;
import com.mdl.hbase.module.dao.DefaultRowMapper;
import com.mdl.hbase.module.domain.HealthEntity;
import com.mdl.hbase.module.service.HealthService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.module.service.impl
 * @Author : xiekun
 * @Create Date : 2020年11月23日 17:31
 * ----------------- ----------------- -----------------
 */

@Service
public class HealthServiceImpl implements HealthService {

  @Autowired
  private HbaseTemplate hbaseTemplate;

  @Override
  public List<Map<String, Object>> query(String tableName, String startRow, String stopRow) {
    Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
    scan.setCaching(5000);
    List<Map<String, Object>> maps = this.hbaseTemplate
        .find(tableName, scan, new DefaultRowMapper());
    return maps;
  }

  @Override
  public Map<String, Object> query(String tableName, String row) {
    Map<String, Object> stringObjectMap = this.hbaseTemplate
        .get(tableName, row, new DefaultRowMapper());
    return stringObjectMap;
  }

  @Override
  public HealthEntity queryHealth(String tableName, String row) {
    HealthEntity obj = (HealthEntity) this.hbaseTemplate
        .get(tableName, row, new DefaultMapper(HealthEntity.class));
    return obj;
  }

  @Override
  public void saveOrUpdate(String tableName, HealthEntity health) {
    this.hbaseTemplate.execute(tableName, (hTableInterface) -> {
      boolean flag = false;
      try {
        List<Put> list = new ArrayList<>();
        //基础列簇
        String rowKey = System.currentTimeMillis() + "_" + health.getName();
        Put putBaseName = getPut(health, HealthEntity.FAMILY, rowKey, "name");
        Put putBaseAge = getPut(health, HealthEntity.FAMILY, rowKey, "age");
        Put putBaseSex = getPut(health, HealthEntity.FAMILY, rowKey, "sex");
        Put putDetailAddress = getPut(health, HealthEntity.FAMILY, rowKey, "address");
        Put putDetailSalary = getPut(health, HealthEntity.FAMILY, rowKey, "salary");
        list.add(putBaseName);
        list.add(putBaseAge);
        list.add(putBaseSex);
        list.add(putDetailAddress);
        list.add(putDetailSalary);
        //插入数据
        hTableInterface.put(list);
        flag = true;
      } catch (Exception e) {
        e.printStackTrace();
      }
      return flag;
    });
  }

  /**
   * 组装插入hbase的值的cell
   */
  private Put getPut(HealthEntity health, String cf, String rowKey, String filedName) {
    Put put = new Put(Bytes.toBytes(rowKey));
    String filedVal = String.valueOf(ObjUtils.getFiledValueByName(health, filedName));
    put.addColumn(
        Bytes.toBytes(cf)
        , Bytes.toBytes(filedName)
        , Bytes.toBytes(filedVal)
    );
    return put;
  }

//  @Override
//  public void saveOrUpdate(String tableName, List<HealthEntity> healths) {
//    this.hbaseTemplate.execute(tableName, (hTableInterface) -> {
//      boolean flag = false;
//      try {
//        List<Put> list = new ArrayList<>();
//        healths.forEach(t -> {
//          Put put = new Put(Bytes.toBytes(t.getAge()));
//          put.addColumn(
//              Bytes.toBytes(System.currentTimeMillis())
//              , Bytes.toBytes(t.getAge())
//              , Bytes.toBytes(t.getAge())
//          );
//          list.add(put);
//        });
//        hTableInterface.put(list);
//        flag = true;
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//      return flag;
//    });
//  }

}
