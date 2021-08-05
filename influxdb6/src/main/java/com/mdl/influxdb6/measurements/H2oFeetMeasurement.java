package com.mdl.influxdb6.measurements;

import java.util.concurrent.TimeUnit;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.measurements
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月05日 22:59
 * ----------------- ----------------- -----------------
 */

@Data
@Measurement(name = "h2o_feet", database = "NOAA_water_database", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class H2oFeetMeasurement implements TicBaseMeasurement {

  @TimeColumn
  @Column(name = "time")
  private String time;

  @Column(name = "location")
  private String location;

  @Column(name = "water_level")
  private String waterLevel;

}
