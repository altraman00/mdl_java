package com.mdl.seckill.service;

import com.mdl.seckill.dto.ExposerDTO;
import com.mdl.seckill.entity.SeckillEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.service
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年03月07日 21:19
 * ----------------- ----------------- -----------------
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class SeckillServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(SeckillServiceTest.class);


  @Autowired
  private SeckillService seckillService;

  @Test
  void queryBySeckillId() {
    long seckillId = 1001;
    SeckillEntity seckillEntity = seckillService.queryBySeckillId(seckillId);
    logger.info("seckill={}", seckillEntity);
  }

  @Test
  void getSeckillList() {
  }

  @Test
  void getById() {
  }

  @Test
  void exportSeckillUrl() {
    long seckillId = 1001;
    ExposerDTO exposerDTO = seckillService.exportSeckillUrl(seckillId);
    logger.info("exposerDTO={}",exposerDTO);

  }

  @Test
  void executeSeckill() {
    long seckillId = 10001;
    String md5 = "";
    seckillService.executeSeckill(seckillId, 13397158888L, md5);
  }
}