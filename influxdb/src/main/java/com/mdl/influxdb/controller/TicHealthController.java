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
 * @Package Name : com.mobvoi.influxdb.controller
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

  @PostMapping("write_point")
  public String insertPoint(@RequestBody HealthPointVO healthPoint) {

    HealthPoint healthPointInfo = HealthPoint.builder()
        .tWwid(healthPoint.getTWwid())
        .tAccountId(healthPoint.getTAccountId())
        .tDataType(healthPoint.getTDataType())
        .tStartTime(healthPoint.getTStartTime())
        .tEndTime(healthPoint.getTEndTime())
        .tDataSourceName(healthPoint.getTDataSourceName())
        .fValue(healthPoint.getFValue())
        .build();

    Point point = Point.measurementByPOJO(healthPointInfo.getClass())
        .addFieldsFromPOJO(healthPointInfo)
        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .build();
    influxDBUtils.write(point);

    return "success";

  }

  @GetMapping("read_point")
  public String getPoint(@RequestParam("wwid") String wwid) {
    HashMap<String, String> params = new HashMap<String, String>(1) {{
      put("wwid", wwid);
    }};
    String tags = influxDBUtils.getTags(params);
    return tags;
  }


}
