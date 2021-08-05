package com.mdl.influxdb6;

import com.mdl.influxdb6.dao.UserTestDao;
import com.mdl.influxdb6.measurements.HealthSessionMeasurement;
import com.mdl.influxdb6.proxy.BaseDaoProxyFactory;
import java.util.List;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.proxydemo2
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 15:26
 * ----------------- ----------------- -----------------
 */
public class MainTest {

  public static void main(String[] args) throws Exception {

    BaseDaoProxyFactory<UserTestDao> factory = new BaseDaoProxyFactory<>();
    factory.setInterfaceClass(UserTestDao.class);
    UserTestDao testDao = factory.getObject();
//    testDao.delete(123L);
//    testDao.get(34242L);
    List<HealthSessionMeasurement> byId = testDao.getById(131L);
    System.out.println(byId);

  }


}
