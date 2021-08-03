package com.mdl.influxdb3.repository.impl;

import com.mdl.influxdb3.measurements.HealthSessionMeasurement;
import com.mdl.influxdb3.repository.HealthDataSessionRepository;
import com.mdl.influxdb3.repository.base.AbstractInfluxdbRepository;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb3.repository
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月29日 23:03
 * ----------------- ----------------- -----------------
 */
@Component
public class HealthDataSessionRepositoryImpl extends AbstractInfluxdbRepository<HealthSessionMeasurement> implements HealthDataSessionRepository {

  @Override
  public void saveSession(HealthSessionMeasurement sessionMeasurement) {
    this.saveAndFlush(sessionMeasurement);
  }

  @Override
  public List<HealthSessionMeasurement> queryDataSessions(HealthSessionMeasurement sessionMeasurement) {
    String tWwid = "123456";
    String sql = String.format("%s'%s'", "select * from tic_sensor where t_wwid = ", tWwid);
    return this.query(sql,HealthSessionMeasurement.class);
  }



}
