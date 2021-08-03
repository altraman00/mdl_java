package com.mdl.influxdb4.repository;

import com.mdl.influxdb4.measurements.HealthSessionMeasurement;
import com.mdl.influxdb4.repository.base.TicBaseRepository;
import java.util.List;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.base.modules.influxdb.service
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月23日 22:36
 * ----------------- ----------------- -----------------
 */

public interface HealthDataSessionRepository extends TicBaseRepository<HealthSessionMeasurement> {

  void saveSession(HealthSessionMeasurement sessionMeasurement);

  List<HealthSessionMeasurement> queryDataSessions(HealthSessionMeasurement sessionMeasurement);

}
