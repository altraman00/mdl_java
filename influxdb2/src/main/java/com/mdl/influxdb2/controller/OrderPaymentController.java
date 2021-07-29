package com.mdl.influxdb2.controller;

import com.mdl.influxdb2.common.BaseResponse;
import com.mdl.influxdb2.config.InfluxMapper;
import com.mdl.influxdb2.model.FitnessPoint;
import com.mdl.influxdb2.model.vo.FitnessVO;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb2.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月14日 22:59
 * ----------------- ----------------- -----------------
 */

@Log4j2
@RestController
public class OrderPaymentController {

  @Autowired
  private InfluxMapper influxMapper;

//  @Autowired
//  public void setInfluxMapper(InfluxMapper influxMapper) {
//    this.influxMapper = influxMapper;
//  }

  @PostMapping("/save")
  public BaseResponse<?> save(@RequestBody FitnessVO fitnessVO) {
    FitnessPoint point = new FitnessPoint();
    point.setTime(fitnessVO.getTime());
    point.setTWwid(fitnessVO.getTWwid());
    point.setTAccountId(fitnessVO.getTAccountId());
    point.setTDataType(fitnessVO.getTDataType());
    point.setTDataSourceName(fitnessVO.getTDataSourceName());
    point.setTStartTime(fitnessVO.getTStartTime());
    point.setTEndTime(fitnessVO.getTEndTime());
    point.setFValue(fitnessVO.getFValue());
    point.setFValue2(fitnessVO.getFValue2());
    point.setFValue3(fitnessVO.getFValue3());
    influxMapper.save(point);
    return BaseResponse.ok();
  }

  @GetMapping("/queryAll")
  public BaseResponse<List<FitnessPoint>> queryAll() {
    List<FitnessPoint> products = influxMapper.query(FitnessPoint.class);
    return BaseResponse.ok(products);
  }

  @GetMapping("/queryByWwid")
  public BaseResponse<List<FitnessPoint>> queryByWwid(String tWwid) {
    String sql = String.format("%s'%s'", "select * from tic_sensor where t_wwid = ", tWwid);
    log.info(sql);
    Query query = new Query(sql, "ticwatch");
    List<FitnessPoint> products = influxMapper.query(query, FitnessPoint.class);
    return BaseResponse.ok(products);
  }

}
