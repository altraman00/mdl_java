package com.mdl.influxdb.model;

import com.mdl.influxdb.utils.JSONUtils;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb.model
 * @Author : xiekun
 * @Desc : InfluxDB中Tag只能是字符串类型，tag有索引，fields没有索引
 * @Create Date : 2021年07月12日 16:55
 * ----------------- ----------------- -----------------
 */

@Data
//@Measurement(name = "tic_sensor",database="servers", retentionPolicy="autogen",timeUnit = TimeUnit.MILLISECONDS)
@Measurement(name = "tic_sensor", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class Sensor {

  /**
   * Column中的name为measurement中的列名
   * 此外,需要注意InfluxDB中时间戳均是以UTC时区保存,在保存以及提取过程中需要注意时区转换
   */
  @TimeColumn
  @Column(name = "time")
  private String time;

  /**
   * 注解中添加tag = true,表示当前字段内容为tag内容,InfluxDB中Tag只能是字符串类型
   */
  @Column(name = "t_wwid", tag = true)
  private String tWwid;

  @Column(name = "t_account_id", tag = true)
  private String tAccountId;

  @Column(name = "t_data_type", tag = true)
  private String tDataType;

  @Column(name = "t_data_source_name", tag = true)
  private String tDataSourceName;

  @Column(name = "t_start_time")
  private long tStartTime;

  @Column(name = "t_end_time")
  private long tEndTime;

  @Column(name = "f_value")
  private int fValue;

  @Column(name = "f_value2")
  private double fValue2;

  @Column(name = "f_value3")
  private double fValue3;


  public static void main(String[] args) {
    Class<?> clasz = Sensor.class;
    Map columnName = getColumnName(clasz);
    String s = JSONUtils.objectToJson(columnName);
    System.out.println(s);
  }


  /**
   * 获取实体类 @Column 的注解名和属性名的map映射
   */
  public static Map getColumnName(Class clazz) {
    Map map = new ConcurrentHashMap<>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Column.class)) {
        Column declaredAnnotation = field.getDeclaredAnnotation(Column.class);
        String column = declaredAnnotation.name();
        map.put(column, field.getName());
      }
    }
    return map;
  }


}
