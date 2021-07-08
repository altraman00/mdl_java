package com.mdl.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class DataSet2 {

  @JsonProperty("data_source_name")
  public String dataSourceName;

  @JsonProperty("min_start_time_ms")
  public long minStartTimeMs;

  private String minStartTime;

  @JsonProperty("max_end_time_ms")
  public long maxEndTimeMs;

  private String maxEndTime;

  public List<DataSet2Point> points;

}
