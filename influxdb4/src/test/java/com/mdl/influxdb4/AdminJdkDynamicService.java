package com.mdl.influxdb4;

import com.mdl.influxdb4.measurements.TicBaseMeasurement;
import com.mdl.influxdb4.repository.base.TicRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月30日 16:03
 * ----------------- ----------------- -----------------
 */
public class AdminJdkDynamicService implements TicRepository {

  @Override
  public void saveAndFlush(TicBaseMeasurement var1) {
    System.out.println("saveAndFlush");
  }

  @Override
  public List query(String hql, Class clazz) {
    System.out.println("query");
    return new ArrayList();
  }
}
