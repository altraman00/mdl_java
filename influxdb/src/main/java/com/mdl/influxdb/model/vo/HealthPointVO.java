package com.mdl.influxdb.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Project : influxdb
 * @Package Name : com.mdl.influxdb.model.tichealth.vo
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年06月15日 17:55
 * ----------------- ----------------- -----------------
 */

@Builder
@Data
public class HealthPointVO {

  private String tWwid;

  private String tAccountId;

  private String tDataType;

  private String tDataSourceName;

  private long tStartTime;

  private long tEndTime;

  private String fKey;

  private int fValue;

}
