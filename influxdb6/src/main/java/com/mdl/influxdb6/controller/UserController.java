package com.mdl.influxdb6.controller;

import com.mdl.influxdb6.config.TicInfluxdbMapper;
import com.mdl.influxdb6.dao.UserTestDao;
import com.mdl.influxdb6.measurements.HealthSessionMeasurement;
import com.mdl.influxdb6.vo.HealthRecordVO;
import com.mdl.influxdb6.vo.HealthRecordVO.DataSetsBean;
import com.mdl.influxdb6.vo.HealthRecordVO.DataSetsBean.PointsBean;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 17:11
 * ----------------- ----------------- -----------------
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource
  private UserTestDao userTestDao;

  @Autowired
  private TicInfluxdbMapper influxdbMapper;

  @PostMapping("/save")
  public String save(String accountId, @RequestBody HealthRecordVO healthRecordVO) {
    DataSetsBean dataSets = healthRecordVO.getData_sets();
    List<PointsBean> points = dataSets.getPoints();
    String data_type = points.get(0).getData_type();
    HealthSessionMeasurement session = new HealthSessionMeasurement();
    session.setId(UUID.randomUUID().toString());
    session.setAccountId(accountId);
    session.setDataType(data_type);
    session.setDataSourceName(dataSets.getData_source_name());
    session.setMinStartTimeMs(dataSets.getMin_start_time_ms());
    session.setMaxEndTimeMs(dataSets.getMax_end_time_ms());
    influxdbMapper.save(session);
    return "success";
  }

  @GetMapping("/byId")
  public List<HealthSessionMeasurement> queryByIdTest(Long id) {
    System.out.println("userTestDao:" + userTestDao);
    List<HealthSessionMeasurement> byId = userTestDao.getById(id);
    return byId;
  }

}
