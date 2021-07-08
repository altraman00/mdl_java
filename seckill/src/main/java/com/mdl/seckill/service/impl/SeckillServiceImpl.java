package com.mdl.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdl.seckill.dao.SeckillMapper;
import com.mdl.seckill.dao.SuccessKilledMapper;
import com.mdl.seckill.dao.cache.RedisDao;
import com.mdl.seckill.dto.ExposerDTO;
import com.mdl.seckill.dto.SeckillExecutionDTO;
import com.mdl.seckill.entity.SeckillEntity;
import com.mdl.seckill.entity.SuccessKilledEntity;
import com.mdl.seckill.enums.SeckillStatEnum;
import com.mdl.seckill.exception.SeckillCloseException;
import com.mdl.seckill.exception.SeckillException;
import com.mdl.seckill.exception.SeckillRepeatException;
import com.mdl.seckill.service.SeckillService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 秒杀库存表 服务实现类
 * </p>
 *
 * @author xiekun
 * @since 2021-03-07
 */
@Service
public class SeckillServiceImpl extends ServiceImpl<SeckillMapper, SeckillEntity> implements
    SeckillService {

  private static final Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

  /**
   * 用于md5加密混淆用的盐
   */
  private final String slat = "fuygihuojnjkbgwehvjoispk#$%^&*()mlknbqihupeok@#$%^wfmlk";

  @Autowired
  private RedisDao redisDao;

  @Autowired
  private SeckillMapper seckillMapper;

  @Autowired
  private SuccessKilledMapper successKilledMapper;


  /**
   * demo
   */
  @Override
  public SeckillEntity queryBySeckillId(long seckillId) {
    QueryWrapper<SeckillEntity> wrapper = new QueryWrapper<>();
    wrapper.eq("seckill_id", seckillId);
    SeckillEntity seckillEntity = seckillMapper.selectOne(wrapper);
    if (logger.isDebugEnabled()) {
      logger.debug("seckillEntity", seckillEntity);
    }
    return seckillMapper.queryBySeckillId(seckillId);
  }

  /**
   * 根据偏移量查询秒杀商品列表
   */
  @Override
  public List<SeckillEntity> getSeckillList() {
    return seckillMapper.queryAll(0, 4);
  }

  /**
   * 根据id查询秒杀的商品信息
   */
  @Override
  public SeckillEntity getById(long seckillId) {
    return seckillMapper.queryBySeckillId(seckillId);
  }

  @Override
  public ExposerDTO exportSeckillUrl(long seckillId) {
    //检查redis中有没有秒杀接口,如果有，直接返回,如果没有，则查询数据库，然后存入redis并返回
    SeckillEntity seckillEntity = redisDao.getSeckill(seckillId);
    if (seckillEntity == null) {
      seckillEntity = seckillMapper.queryBySeckillId(seckillId);
      if (seckillEntity == null) {
        return new ExposerDTO(false, seckillId);
      } else {
        redisDao.putSeckill(seckillEntity);
      }
    }
    LocalDateTime startTime = dateToLocalDateTime(seckillEntity.getStartTime());
    LocalDateTime endTime = dateToLocalDateTime(seckillEntity.getEndTime());
    LocalDateTime now = LocalDateTime.now();

    //还未开始或者已经结束
    if (now.isBefore(startTime) || now.isAfter(endTime)) {
      return new ExposerDTO(false, seckillId, now, startTime, endTime);
    }
    //转化成特定加密的md5返回，不可逆
    String md5 = getMd5(seckillId);
    return new ExposerDTO(true, md5, seckillId);
  }


  /**
   * 优化前
   *
   * 执行秒杀
   * 使用注解控制事务方法优点：
   * 1、开发团队达成一致约定，明确标注事务方法的变成风格
   * 2、保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外
   * 3、不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
   */
  @Transactional
  public SeckillExecutionDTO executeSeckill_bad(long seckillId, long userPhone, String md5)
      throws SeckillRepeatException, SeckillCloseException {
    if (md5 == null || !md5.equals(getMd5(seckillId))) {
      throw new SeckillException("seckill data rewrite");
    }

    //执行秒杀逻辑：减库存 + 记录购买行为
    LocalDateTime now = LocalDateTime.now();

    try {
      //减库存
      int updateCount = seckillMapper.reduceNumber(seckillId, now);
      if (updateCount < 0) {
        throw new SeckillCloseException("seckill is closed");
      } else {
        //记录购买记录
        int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);

        //唯一:seckillId,userPhone
        if (insertCount <= 0) {
          //重复秒杀
          throw new SeckillRepeatException("seckill repeated");
        } else {
          //秒杀成功
          SuccessKilledEntity successKilledEntity = successKilledMapper
              .queryByIdWithSeckill(seckillId, userPhone);
          return new SeckillExecutionDTO(seckillId, SeckillStatEnum.SUCCESS, successKilledEntity);
        }
      }
    } catch (SeckillRepeatException re) {
      throw re;
    } catch (SeckillCloseException ce) {
      throw ce;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      //所有编译期异常，转化为运行期异常
      throw new SeckillException("seckill inner error:" + e.getMessage());
    }
  }


  /**
   * 优化后
   *
   * 回顾上面事务执行顺序
   * update 减库存rowLock --> (网络延迟 && GC) --> insert购买明细 --> (网络延迟 && GC) --> commit/rollback freeLock
   *
   * 以上整个环节增加了事务的时长，会导致行级锁长时间得不到释放，同一行的库存一直处于阻塞状态，主要出现在网络延时阶段
   *
   * 优化：
   * 缩短时间 ： insert购买明细 --> (网络延迟 && GC) --> update 减库存rowLock --> (网络延迟 && GC) --> commit/rollback freeLock
   *
   * 解释：insert购买明细部分可以挡住重复秒杀部分，延时部分减少，降低mysql的rowLock的持有时间
   */
  @Override
  @Transactional
  public SeckillExecutionDTO executeSeckill(long seckillId, long userPhone, String md5)
      throws SeckillRepeatException, SeckillCloseException {
    if (md5 == null || !md5.equals(getMd5(seckillId))) {
      throw new SeckillException("seckill data rewrite");
    }

    //执行秒杀逻辑：减库存 + 记录购买行为
    LocalDateTime now = LocalDateTime.now();

    try {
      //记录购买记录
      int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
      //唯一:seckillId,userPhone
      if (insertCount <= 0) {
        //重复秒杀
        throw new SeckillRepeatException("seckill repeated");
      } else {
        //减库存
        int updateCount = seckillMapper.reduceNumber(seckillId, now);
        if (updateCount < 0) {
          //没有更新到记录，秒杀结束
          throw new SeckillCloseException("seckill is closed");
        }
        //秒杀成功
        SuccessKilledEntity successKilledEntity = successKilledMapper
            .queryByIdWithSeckill(seckillId, userPhone);
        return new SeckillExecutionDTO(seckillId, SeckillStatEnum.SUCCESS, successKilledEntity);
      }
    } catch (SeckillRepeatException re) {
      throw re;
    } catch (SeckillCloseException ce) {
      throw ce;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      //所有编译期异常，转化为运行期异常
      throw new SeckillException("seckill inner error:" + e.getMessage());
    }
  }

  /**
   * 生成md5
   */
  private String getMd5(long seckillId) {
    String base = seckillId + "/" + slat;
    return DigestUtils.md5DigestAsHex(base.getBytes());
  }

  public LocalDateTime dateToLocalDateTime(Date date) {
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    return instant.atZone(zoneId).toLocalDateTime();
  }


}
