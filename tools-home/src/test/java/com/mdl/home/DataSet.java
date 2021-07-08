// Copyright 2016 Mobvoi Inc. All Rights Reserved

package com.mdl.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anka Zhao, "kei@mobvoi.com"
 * @date 2016年7月13日
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DataSet {

  @JsonProperty("data_source_name")
  public String dataSourceName;

  @JsonProperty("min_start_time_ms")
  public long minStartTimeMs;

  public String minStartTime;

  @JsonProperty("max_end_time_ms")
  public long maxEndTimeMs;

  public String maxEndTime;

  public List<DataSetPoint> points;

}
