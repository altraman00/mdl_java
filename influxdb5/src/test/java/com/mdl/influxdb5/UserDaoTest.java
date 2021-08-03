package com.mdl.influxdb5;

import com.mdl.influxdb5.dao.UserTestDao;
import com.mdl.influxdb5.annotations.MyDaoScan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 16:56
 * ----------------- ----------------- -----------------
 */

@MyDaoScan(basePackage = "com.mdl.influxdb5.dao")
@SpringBootTest
public class UserDaoTest {

  @Autowired
  private UserTestDao userTestDao;

  @Test
  void queryByIdTest() {
    userTestDao.getById(123L);
  }

}
