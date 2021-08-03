package com.mdl.influxdb3.service;

import com.mdl.influxdb3.measurements.HealthSessionMeasurement;
import com.mdl.influxdb3.vo.HealthRecordQueryVO;
import com.mdl.influxdb3.vo.HealthRecordVO;
import java.util.List;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.appwatch.service.ticwatch
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月28日 18:15
 * ----------------- ----------------- -----------------
 */
public interface ITicHealthService {

  /**
   * 保存
   */
  void save(String accountId, HealthRecordVO healthRecordVO);

  /**
   * 查询健康数据
   */
  List<HealthSessionMeasurement> queryList(HealthRecordQueryVO queryVO);

}
