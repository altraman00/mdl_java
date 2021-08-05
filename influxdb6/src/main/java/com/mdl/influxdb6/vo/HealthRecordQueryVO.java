package com.mdl.influxdb6.vo;

import lombok.Data;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.framework.domain.vo
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月29日 14:53
 * ----------------- ----------------- -----------------
 */

@Data
public class HealthRecordQueryVO {

  private String account_id;

  private String dats_source_name;

  private String data_type;

  private long max_end_time_ms;

  private long min_start_time_ms;

}
