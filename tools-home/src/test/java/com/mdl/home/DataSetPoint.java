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
 * @Create Date : 2021年05月27日 18:26
 * ----------------- ----------------- -----------------
 */

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DataSetPoint {

  @JsonProperty("start_time_millis")
  public long startTimeMillis;

  public String startTime;

  @JsonProperty("end_time_millis")
  public long endTimeMillis;

  public String endTime;

  @JsonProperty("data_type")
  public String dataType;

  @JsonIgnore
  public double fpVal;

  @JsonIgnore
  public int intVal;

  @JsonProperty("string_val")
  public String stringVal;

  public String stringValue;

  /**
   * 是否删除, 0 表示 未删除，1 表示删除.
   */
  @JsonIgnore
  private byte deleted;



}
