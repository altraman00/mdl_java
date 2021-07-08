package com.mdl.hbase.module.dao;

import com.mdl.hbase.common.JSONUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.RowMapper;


/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.module.dao
 * @Author : xiekun
 * @Desc : 返回具体对象
 * @Create Date : 2020年11月27日 14:35
 * ----------------- ----------------- -----------------
 */

@Slf4j
public class DefaultMapper<T> implements RowMapper<T> {

  private String columnFamily;
  private Map<String, Field> colums = new HashMap<>();
  private Class<T> clazz;

  public DefaultMapper(Class<T> clazz) {
    super();
    this.clazz = clazz;
    map();
  }


  /**
   * hbase只能使用基础类型，不支持包装类Integer，Long，Boolean等
   */
  @Override
  public T mapRow(Result result, int i) throws IllegalAccessException, InstantiationException {
    if (result.size() == 0) {
      return null;
    }

    T obj = clazz.newInstance();
    for (Entry<String, Field> entry : colums.entrySet()) {
      Field f = entry.getValue();
      if (columnFamily != null) {
        byte[] val = result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(entry.getKey()));
        if (val == null) {
          continue;
        }
        Class<?> type = f.getType();
        log.info("type:{}", type);
        if (Integer.class.equals(type) || int.class.equals(type)) {
          int a = Integer.valueOf(Bytes.toString(val)).intValue();
          f.setInt(obj, a);
        } else if (Long.class.equals(type) || long.class.equals(type)) {
          f.setLong(obj, Long.valueOf(Bytes.toString(val)));
        } else if (Boolean.class.equals(type) || boolean.class.equals(type)) {
          f.setBoolean(obj, Boolean.valueOf(Bytes.toString(val)));
        } else if (String.class.equals(type)) {
          f.set(obj, Bytes.toString(val));
        } else {
          log.info("**************类型未知**************:{}", type);
          f.set(obj, Bytes.toString(val));
        }
      }
    }
    return obj;
  }

  /**
   * 获取所有字段 字段名称与hbase一一对应，存在一个final+static字段为列簇字段名称
   */
  private void map() {
    // 得到类中的所有属性集合
    Field[] fs = clazz.getDeclaredFields();
    for (int i = 0; i < fs.length; i++) {
      Field f = fs[i];
      // 设置些属性是可以访问的
      f.setAccessible(true);
      int m = f.getModifiers();
      if (!Modifier.isFinal(m) && !Modifier.isStatic(m)) {
        colums.put(f.getName(), f);
      } else if ("FAMILY".equals(f.getName())) {
        try {
          columnFamily = f.get(clazz).toString();
        } catch (IllegalArgumentException | IllegalAccessException e) {
          columnFamily = "f0";
        }
      }
    }
  }


  public List<Mutation> object2mutation(T t) throws IllegalAccessException, IOException {
    List<Mutation> list = new ArrayList<>();
    Field rowkeyField = colums.get("rowkey");
    String rowkey = rowkeyField.get(t).toString();
    for (Entry<String, Field> entry : colums.entrySet()) {
      Put put = new Put(Bytes.toBytes(rowkey));
      String conlumn = entry.getKey();
      Object val = entry.getValue().get(t);
      if (val != null) {
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(conlumn),
            Bytes.toBytes(JSONUtils.objectToJson(val)));
        list.add(put);
      }
    }
    return list;
  }

}
