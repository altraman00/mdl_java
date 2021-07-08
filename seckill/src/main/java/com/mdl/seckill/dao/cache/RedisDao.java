package com.mdl.seckill.dao.cache;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mdl.seckill.config.RedisUtil;
import com.mdl.seckill.entity.SeckillEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.dao.cache
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年03月08日 10:48
 * ----------------- ----------------- -----------------
 */

@Component
public class RedisDao {

  private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);

  @Autowired
  RedisUtil redisUtil;


  /**
   * 根据id获取seckill
   */
  public SeckillEntity getSeckill(long seckillId) {
    SeckillEntity seckillEntity;
    try {
      String seckillRedisKey = getSeckillRedisKey(seckillId);
      Object obj = redisUtil.get(seckillRedisKey);
      if(obj == null){
        return null;
      }
      JSONObject jsonObject = JSONUtil.parseObj(obj);
      seckillEntity = JSONUtil.toBean(jsonObject, SeckillEntity.class);
      return seckillEntity;
    } catch (Exception e) {
      logger.error("RedisDao getSeckill error", e);
    }
    return null;
  }


  /**
   * 存储seckill
   */
  public void putSeckill(SeckillEntity seckillEntity) {
    Long seckillId = seckillEntity.getSeckillId();
    String seckillRedisKey = getSeckillRedisKey(seckillId);
    redisUtil.setIfAbsent(seckillRedisKey, seckillEntity, 60 * 60);
  }


  /**
   * 根据id获取redis的key
   */
  private String getSeckillRedisKey(long seckillId) {
    return "seckill:" + seckillId;
  }


}
