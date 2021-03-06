package com.mdl.influxdb.model;

import lombok.Builder;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * @Project : influxdb
 * @Package Name : com.mdl.influxdb.model.tichealth
 * @Author : xiekun
 * @Desc : InfluxDB中Tag只能是字符串类型，tag有索引，fields没有索引
 * @Create Date : 2021年06月15日 17:58
 * ----------------- ----------------- -----------------
 */


@Builder
@Data
@Measurement(name = "health_point")
public class HealthPoint {

  /**
   * Column中的name为measurement中的列名
   * 此外,需要注意InfluxDB中时间戳均是以UTC时区保存,在保存以及提取过程中需要注意时区转换
   */
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

  @Column(name = "f_level")
  private Double fLevel;

  @Column(name = "f_value")
  private int fValue;

}
