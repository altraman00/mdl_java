package com.mdl.influxdb5.dao;

import com.mdl.influxdb5.annotations.InfluxDbHqlMapper;
import com.mdl.influxdb5.proxy.IBaseDao;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:25
 * ----------------- ----------------- -----------------
 */

@InfluxDbHqlMapper(name = "user_test_dao")
public interface UserTestDao extends IBaseDao {

  String getById(Long id);

}
