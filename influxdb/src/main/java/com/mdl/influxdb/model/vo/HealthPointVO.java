package com.mdl.influxdb.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Project : influxdb
 * @Package Name : com.mobvoi.influxdb.model.tichealth.vo
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

  private String tStartTime;

  private String tEndTime;

  private String tDataSourceName;

  private String fValue;

}
