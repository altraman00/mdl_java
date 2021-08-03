package com.mdl.influxdb5;

import com.mdl.influxdb5.dao.UserTestDao;
import com.mdl.influxdb5.proxy.BaseDaoProxyFactory;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxydemo2
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
    String byId = testDao.getById(131L);
    System.out.println(byId);

  }


}
