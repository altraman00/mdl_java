package com.mdl.seckill.dao;

import com.mdl.seckill.entity.SeckillEntity;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.dao
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年03月07日 16:14
 * ----------------- ----------------- -----------------
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class SeckillMapperTest {

  @Resource
  private SeckillMapper seckillMapper;

  @Test
  void reduceNumber() {
  }

  @Test
  void queryById() {
    long seckillId=1000;
    SeckillEntity seckill=seckillMapper.queryBySeckillId(seckillId);
    System.out.println(seckill.getName());
    System.out.println(seckill);
  }

  @Test
  void queryAll() {
  }
}