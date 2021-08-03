package com.mdl.influxdb3.measurements;

import com.mdl.influxdb3.measurements.TicBaseMeasurement;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.base.modules.influxdb.service
 * @Author : xiekun
 * @Desc : Column中的name为measurement中的列名
 *         注解中添加tag = true,表示当前字段内容为tag内容,InfluxDB中Tag只能是字符串类型
 *         此外,需要注意InfluxDB中时间戳均是以UTC时区保存,在保存以及提取过程中需要注意时区转换
 * @Create Date : 2021年07月23日 22:36
 * ----------------- ----------------- -----------------
 */

@Data
@Measurement(name = "tic_health_session", database = "ticwatch", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class HealthSessionMeasurement implements TicBaseMeasurement {

  @TimeColumn
  @Column(name = "time")
  private String time;

  @Column(name = "id")
  private String id;

  @Column(name = "account_id", tag = true)
  private String accountId;

  @Column(name = "data_source_name", tag = true)
  private String dataSourceName;

  @Column(name = "data_type", tag = true)
  private String dataType;

  @Column(name = "min_start_time_ms")
  private long minStartTimeMs;

  @Column(name = "max_end_time_ms")
  private long maxEndTimeMs;


}
