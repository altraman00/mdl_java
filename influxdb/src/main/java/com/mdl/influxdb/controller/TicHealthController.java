package com.mdl.influxdb.controller;

import com.mdl.influxdb.config.InfluxDBUtils;
import com.mdl.influxdb.model.HealthPoint;
import com.mdl.influxdb.model.vo.HealthPointVO;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : influxdb
 * @Package Name : com.mdl.influxdb.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年06月15日 17:48
 * ----------------- ----------------- -----------------
 */


@RequestMapping("tichealth")
@RestController
public class TicHealthController {

  @Autowired
  private InfluxDBUtils influxDBUtils;

  /**
   * 写入单条数据
   * @param healthPoint
   * @return
   */
  @PostMapping("write_point")
  public String writePoint(@RequestBody HealthPointVO healthPoint) {
    HealthPoint healthPointInfo = HealthPoint.builder()
        .tWwid(healthPoint.getTWwid())
        .tAccountId(healthPoint.getTAccountId())
        .tDataType(healthPoint.getTDataType())
        .tStartTime(healthPoint.getTStartTime())
        .tEndTime(healthPoint.getTEndTime())
        .tDataSourceName(healthPoint.getTDataSourceName())
        .fValue(healthPoint.getFValue())
        .build();

    influxDBUtils.writeOne(healthPointInfo);
    return "success";
  }

  /**
   * 写入多条数据
   * @param healthPoint
   * @return
   */
  @PostMapping("write_list")
  public String writePointList(@RequestBody HealthPointVO healthPoint) {
    HealthPoint healthPointInfo = HealthPoint.builder()
        .tWwid(healthPoint.getTWwid())
        .tAccountId(healthPoint.getTAccountId())
        .tDataType(healthPoint.getTDataType())
        .tStartTime(healthPoint.getTStartTime())
        .tEndTime(healthPoint.getTEndTime())
        .tDataSourceName(healthPoint.getTDataSourceName())
        .fValue(healthPoint.getFValue())
        .build();

    influxDBUtils.writeOne(healthPointInfo);
    return "success";
  }



  /**
   * 读取tags
   * @param wwid
   * @return
   */
  @GetMapping("read_point_tags")
  public String getPoint(@RequestParam("wwid") String wwid) {
    HashMap<String, String> params = new HashMap<String, String>(1) {{
      put("wwid", wwid);
    }};
    String tags = influxDBUtils.getTags(params);
    return tags;
  }


}
