package com.mdl.influxdb6;

import com.mdl.influxdb6.dao.UserTestDao;
import com.mdl.influxdb6.annotations.MyDaoScan;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 16:56
 * ----------------- ----------------- -----------------
 */

@MyDaoScan(basePackage = "com.mdl.influxdb6.dao")
@SpringBootTest
public class UserDaoTest {

  @Resource
  private UserTestDao userTestDao;

  @Test
  void queryByIdTest() {
    userTestDao.getById(123L);
  }

}
