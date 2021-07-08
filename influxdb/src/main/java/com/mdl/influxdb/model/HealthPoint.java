package com.mdl.influxdb.model;

import lombok.Builder;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * @Project : influxdb
 * @Package Name : com.mobvoi.influxdb.model.tichealth
 * @Author : xiekun
 * @Desc :
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
   * 注解中添加tag = true,表示当前字段内容为tag内容
   */
  @Column(name = "t_wwid", tag = true)
  private String tWwid;

  @Column(name = "t_account_id", tag = true)
  private String tAccountId;

  @Column(name = "t_data_type", tag = true)
  private String tDataType;

  @Column(name = "t_start_time", tag = true)
  private String tStartTime;

  @Column(name = "t_end_time", tag = true)
  private String tEndTime;

  @Column(name = "t_data_source_name", tag = true)
  private String tDataSourceName;

  @Column(name = "f_value")
  private String fValue;

}
