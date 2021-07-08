package com.mdl.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdl.seckill.dto.ExposerDTO;
import com.mdl.seckill.dto.SeckillExecutionDTO;
import com.mdl.seckill.entity.SeckillEntity;
import com.mdl.seckill.exception.SeckillRepeatException;
import com.mdl.seckill.exception.SeckillCloseException;
import java.util.List;

/**
 * <p>
 * 秒杀库存表 服务类
 * 站在"使用者"的角度设计接口
 * 三个方面：方法定义粒度、参数、返回类型（return 类型/entity/异常）
 * </p>
 *
 * @author xiekun
 * @since 2021-03-07
 */
public interface SeckillService extends IService<SeckillEntity> {

  SeckillEntity queryBySeckillId(long seckillId);

  /**
   * 查询所有秒杀记录
   */
  List<SeckillEntity> getSeckillList();

  /**
   * 查询单个秒杀记录
   */
  SeckillEntity getById(long seckillId);

  /**
   * 秒杀开启时输出秒杀地址
   * 否则输出系统时间和秒杀时间
   */
  ExposerDTO exportSeckillUrl(long seckillId);

  /**
   * 执行秒杀
   */
  SeckillExecutionDTO executeSeckill(long seckillId, long userPhone, String md5)
      throws SeckillRepeatException, SeckillCloseException;


}
