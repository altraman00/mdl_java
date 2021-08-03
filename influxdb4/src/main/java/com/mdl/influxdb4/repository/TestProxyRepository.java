package com.mdl.influxdb4.repository;

import com.mdl.influxdb4.annotations.Influxhql;
import com.mdl.influxdb4.measurements.HealthSessionMeasurement;
import com.mdl.influxdb4.repository.base.TicRepository;
import java.util.List;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4.repository
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月30日 17:18
 * ----------------- ----------------- -----------------
 */


public interface TestProxyRepository extends TicRepository<HealthSessionMeasurement> {

  @Influxhql(value = "select * from tic_health",database = "ticwatch",measurement = "tic_health")
  List<HealthSessionMeasurement> queryData(String accountId);

}
