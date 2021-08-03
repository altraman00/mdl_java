package com.mdl.influxdb4.service.impl;

import com.mdl.influxdb4.measurements.HealthSessionMeasurement;
import com.mdl.influxdb4.repository.HealthDataSessionRepository;
import com.mdl.influxdb4.service.ITicHealthService;
import com.mdl.influxdb4.vo.HealthRecordQueryVO;
import com.mdl.influxdb4.vo.HealthRecordVO;
import com.mdl.influxdb4.vo.HealthRecordVO.DataSetsBean;
import com.mdl.influxdb4.vo.HealthRecordVO.DataSetsBean.PointsBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.appwatch.service.ticwatch.impl
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月28日 18:16
 * ----------------- ----------------- -----------------
 */
@Service
public class TicHealthServiceImpl  implements ITicHealthService {

  @Autowired
  private HealthDataSessionRepository dataSessionRepository;

  @Override
  public void save(String accountId, HealthRecordVO healthRecordVO) {
    DataSetsBean dataSets = healthRecordVO.getData_sets();
    List<PointsBean> points = dataSets.getPoints();
    String data_type = points.get(0).getData_type();
    HealthSessionMeasurement session = new HealthSessionMeasurement();
    session.setId("xxxxxxx");
    session.setAccountId(accountId);
    session.setDataType(data_type);
    session.setDataSourceName(dataSets.getData_source_name());
    session.setMinStartTimeMs(dataSets.getMin_start_time_ms());
    session.setMaxEndTimeMs(dataSets.getMax_end_time_ms());
    dataSessionRepository.saveSession(session);

  }

  @Override
  public List<HealthSessionMeasurement> queryList(HealthRecordQueryVO queryVO) {
    HealthSessionMeasurement sessionMeasurement = new HealthSessionMeasurement();
    sessionMeasurement.setMinStartTimeMs(queryVO.getMin_start_time_ms());
    sessionMeasurement.setMaxEndTimeMs(queryVO.getMax_end_time_ms());
    sessionMeasurement.setDataType(queryVO.getData_type());
    sessionMeasurement.setDataSourceName(queryVO.getDats_source_name());
    sessionMeasurement.setAccountId(queryVO.getAccount_id());
    List<HealthSessionMeasurement> list = dataSessionRepository.queryDataSessions(sessionMeasurement);
    return list;
  }


}
