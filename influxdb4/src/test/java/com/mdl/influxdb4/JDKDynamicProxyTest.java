package com.mdl.influxdb4;

import com.mdl.influxdb4.measurements.HealthSessionMeasurement;
import com.mdl.influxdb4.proxy.TicBaseRepositoryJDKDynamicProxy;
import com.mdl.influxdb4.repository.TestProxyRepository;
import java.util.List;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月30日 16:19
 * ----------------- ----------------- -----------------
 */
public class JDKDynamicProxyTest {

//  public static void main(String[] args) {
//    TicRepository proxy = TicBaseRepositoryJDKDynamicProxy.newMapperProxy(TicRepository.class);
//
//    HealthSessionMeasurement measurement = new HealthSessionMeasurement();
//    measurement.setAccountId("1111");
//
//    proxy.saveAndFlush(measurement);
//
////    proxy.query("select * from table", HealthSessionMeasurement.class);
//
//  }

  public static void main(String[] args) {
    TestProxyRepository proxy = TicBaseRepositoryJDKDynamicProxy.newMapperProxy(TestProxyRepository.class);

    HealthSessionMeasurement measurement = new HealthSessionMeasurement();
    measurement.setAccountId("1111");

    List<HealthSessionMeasurement> healthSessionMeasurements = proxy.queryData("123456");

//    proxy.saveAndFlush(measurement);

//    proxy.query("select * from table", HealthSessionMeasurement.class);

  }


}
