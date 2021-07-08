package com.mdl.hbase.module.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.RowMapper;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.module.dao
 * @Author : xiekun
 * @Desc : 返回map类型
 * @Create Date : 2020年11月23日 17:31
 * ----------------- ----------------- -----------------
 */

public class DefaultRowMapper implements RowMapper<Map<String, Object>> {

  @Override
  public Map<String, Object> mapRow(Result result, int i){
    List<Cell> ceList = result.listCells();
    Map<String, Object> map = new HashMap<>(16);
    if (ceList != null && ceList.size() > 0) {
      for (Cell cell : ceList) {
        byte[] familyArray = cell.getFamilyArray();
        int familyOffset = cell.getFamilyOffset();
        byte familyLength = cell.getFamilyLength();

        byte[] qualifierArray = cell.getQualifierArray();
        int qualifierOffset = cell.getQualifierOffset();
        int qualifierLength = cell.getQualifierLength();

        String key1 = Bytes.toString(familyArray, familyOffset, familyLength);
        String key2 = Bytes.toString(qualifierArray, qualifierOffset, qualifierLength);
        String key = key1 + ":" + key2;

        byte[] valueArray = cell.getValueArray();
        int valueOffset = cell.getValueOffset();
        int valueLength = cell.getValueLength();

        String val = Bytes.toString(valueArray, valueOffset, valueLength);
        map.put(key, val);
      }
    }
    return map;
  }

}
