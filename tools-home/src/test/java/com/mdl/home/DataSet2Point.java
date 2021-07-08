package com.mdl.home;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年05月27日 19:21
 * ----------------- ----------------- -----------------
 */

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DataSet2Point {

  @JsonProperty("start_time_millis")
  public long startTimeMillis;

  @JsonProperty("startTime")
  public String startTime;

  @JsonProperty("end_time_millis")
  public long endTimeMillis;

  @JsonProperty("endTime")
  public String endTime;

  public String dataType;

  public double fpVal;

  public int intVal;

  @JsonProperty("string_val")
  public String stringVal;

  /**
   * 是否删除, 0 表示 未删除，1 表示删除.
   */
  @JsonIgnore
  private byte deleted;

}
