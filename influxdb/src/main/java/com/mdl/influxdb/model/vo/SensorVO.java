package com.mdl.influxdb.model.vo;

import lombok.Builder;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb.model
 * @Author : xiekun
 * @Desc : InfluxDB中Tag只能是字符串类型，tag有索引，fields没有索引
 * @Create Date : 2021年07月12日 16:55
 * ----------------- ----------------- -----------------
 */

@Builder
@Data
public class SensorVO {

  private String time;

  private String tWwid;

  private String tAccountId;

  private String tDataType;

  private String tDataSourceName;

  private long tStartTime;

  private long tEndTime;

  private String fKey;

  private int fValue;

  private float fValue2;

  private double fValue3;

}
