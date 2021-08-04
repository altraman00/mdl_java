package com.mdl.influxdb5.dao;

import com.mdl.influxdb5.annotations.InfluxDbHql;
import com.mdl.influxdb5.annotations.InfluxDbHqlMapper;
import com.mdl.influxdb5.measurements.HealthSessionMeasurement;
import com.mdl.influxdb5.proxy.IBaseDao;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:25
 * ----------------- ----------------- -----------------
 */

@InfluxDbHqlMapper(database = "ticwatch",measurement = "tic_health")
public interface UserTestDao extends IBaseDao<HealthSessionMeasurement> {

  @InfluxDbHql(value = "select * from tic_health")
  String getById(Long id);

}
