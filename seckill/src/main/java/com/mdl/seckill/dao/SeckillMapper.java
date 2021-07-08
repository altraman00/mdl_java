package com.mdl.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mdl.seckill.entity.SeckillEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 秒杀库存表 Mapper 接口
 * </p>
 *
 * @author xiekun
 * @since 2021-03-07
 */

@Repository
public interface SeckillMapper extends BaseMapper<SeckillEntity> {

  /**
   * 减库存
   *
   * @return 如果影响行数>1，表示更新库存的记录行数
   */
  int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") LocalDateTime killTime);

  /**
   * 根据id查询秒杀的商品信息
   */
  SeckillEntity queryBySeckillId(long seckillId);

  /**
   * 根据偏移量查询秒杀商品列表
   */
  List<SeckillEntity> queryAll(@Param("off") int off, @Param("limit") int limit);

}
