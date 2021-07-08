package com.mdl.home;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年05月28日 12:20
 * ----------------- ----------------- -----------------
 */

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SportPoint {

  public long timestamp;

  // 绝对时间戳.
  @JSONField(name = "wall_clock_timestamp")
  public long wallClockTimestamp;

  @JSONField(name = "gps_state")
  public String gpsState;

  // lat,lng
  @JSONField(name = "gps_point")
  public String gpsPoint;

  @JSONField(name = "velocity")
  public double velocity;

  @JSONField(name = "cumulative_distance")
  public int cumulativeDistance;

  @JSONField(name = "cumulative_steps")
  public int cumulativeSteps;

  @JSONField(name = "heart_rate")
  public int heartRate;
  // 标明该点为继续运动的第一个点.
  public boolean resume;

  @JSONField(name = "swim_trips")
  public int swimTrips;

  @JSONField(name = "swim_distance")
  public int swimDistance;

  @JSONField(name = "swim_stroke")
  public int swimStroke;

  @JSONField(name = "swim_type")
  public String swimType;

  @JSONField(name = "elevation")
  public float elevation;

}
