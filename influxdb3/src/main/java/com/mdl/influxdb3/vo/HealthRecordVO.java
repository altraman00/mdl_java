package com.mdl.influxdb3.vo;

import java.util.List;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.framework.domain.vo
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月28日 18:17
 * ----------------- ----------------- -----------------
 */
public class HealthRecordVO {

  /**
   * data_sets : {"data_source_name":"raw:d7e1e4eca715cb9398c1f685","max_end_time_ms":1625480761000,"min_start_time_ms":1625480761000,"points":[{"data_type":"heart_health","deleted":0,"end_time_millis":1625480761000,"start_time_millis":1625480761000,"string_val":"2,12"}]}
   */

  private DataSetsBean data_sets;

  public DataSetsBean getData_sets() {
    return data_sets;
  }

  public void setData_sets(DataSetsBean data_sets) {
    this.data_sets = data_sets;
  }

  public static class DataSetsBean {

    /**
     * data_source_name : raw:d7e1e4eca715cb9398c1f685
     * max_end_time_ms : 1625480761000
     * min_start_time_ms : 1625480761000
     * points : [{"data_type":"heart_health","deleted":0,"end_time_millis":1625480761000,"start_time_millis":1625480761000,"string_val":"2,12"}]
     */

    private String data_source_name;
    private long max_end_time_ms;
    private long min_start_time_ms;
    private List<PointsBean> points;

    public String getData_source_name() {
      return data_source_name;
    }

    public void setData_source_name(String data_source_name) {
      this.data_source_name = data_source_name;
    }

    public long getMax_end_time_ms() {
      return max_end_time_ms;
    }

    public void setMax_end_time_ms(long max_end_time_ms) {
      this.max_end_time_ms = max_end_time_ms;
    }

    public long getMin_start_time_ms() {
      return min_start_time_ms;
    }

    public void setMin_start_time_ms(long min_start_time_ms) {
      this.min_start_time_ms = min_start_time_ms;
    }

    public List<PointsBean> getPoints() {
      return points;
    }

    public void setPoints(List<PointsBean> points) {
      this.points = points;
    }

    public static class PointsBean {

      /**
       * data_type : heart_health
       * deleted : 0
       * end_time_millis : 1625480761000
       * start_time_millis : 1625480761000
       * string_val : 2,12
       */

      private String data_type;
      private long end_time_millis;
      private long start_time_millis;
      private String string_val;

      public String getData_type() {
        return data_type;
      }

      public void setData_type(String data_type) {
        this.data_type = data_type;
      }

      public long getEnd_time_millis() {
        return end_time_millis;
      }

      public void setEnd_time_millis(long end_time_millis) {
        this.end_time_millis = end_time_millis;
      }

      public long getStart_time_millis() {
        return start_time_millis;
      }

      public void setStart_time_millis(long start_time_millis) {
        this.start_time_millis = start_time_millis;
      }

      public String getString_val() {
        return string_val;
      }

      public void setString_val(String string_val) {
        this.string_val = string_val;}}}}
