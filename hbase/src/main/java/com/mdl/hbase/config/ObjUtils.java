package com.mdl.hbase.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.config
 * @Author : xiekun
 * @Create Date : 2020年11月27日 10:13
 * ----------------- ----------------- -----------------
 */
public class ObjUtils {

  /**
   * 获取对象属性名数组
   */
  public static String[] getFiledName(Object obj) {
    Field[] fields = obj.getClass().getDeclaredFields();
    String[] filedNames = new String[fields.length];
    for (int i = 0; i < fields.length; i++) {
      filedNames[i] = fields[i].getName();
    }
    return filedNames;
  }

  /**
   * 根据属性名获取属性值
   */
  public static Object getFiledValueByName(Object obj, String fieldName) {
    String firstLetter = fieldName.substring(0, 1).toUpperCase();
    String getter = "get" + firstLetter + fieldName.substring(1);
    try {
      Method method = obj.getClass().getMethod(getter, new Class[]{});
      Object val = method.invoke(obj, new Object[]{});
      return val;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


}
