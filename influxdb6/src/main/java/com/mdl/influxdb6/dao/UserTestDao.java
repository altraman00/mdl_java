package com.mdl.influxdb6.dao;

import com.mdl.influxdb6.annotations.InfluxDbHql;
import com.mdl.influxdb6.annotations.InfluxDbHqlMapper;
import com.mdl.influxdb6.measurements.HealthSessionMeasurement;
import com.mdl.influxdb6.proxy.IBaseDao;
import java.util.List;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:25
 * ----------------- ----------------- -----------------
 */

@InfluxDbHqlMapper(database = "ticwatch",measurement = "tic_health_session")
public interface UserTestDao extends IBaseDao<HealthSessionMeasurement> {

  @InfluxDbHql(value = "select * from tic_health_session")
  List<HealthSessionMeasurement> getById(Long id);

}
