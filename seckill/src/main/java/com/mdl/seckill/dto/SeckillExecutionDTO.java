package com.mdl.seckill.dto;

import com.mdl.seckill.entity.SuccessKilledEntity;
import com.mdl.seckill.enums.SeckillStatEnum;
import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.dto
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年03月07日 17:30
 * ----------------- ----------------- -----------------
 */

@Data
public class SeckillExecutionDTO {

  private long seckillId;

  /**
   * 秒杀执行结果状态
   */
  private int state;

  private String stateInfo;

  /**
   * 秒杀成功对象
   */
  private SuccessKilledEntity successKilled;

  public SeckillExecutionDTO() {
  }

  public SeckillExecutionDTO(long seckillId, int state, String stateInfo) {
    this.seckillId = seckillId;
    this.state = state;
    this.stateInfo = stateInfo;
  }

  public SeckillExecutionDTO(long seckillId, SeckillStatEnum seckillStatEnum,
      SuccessKilledEntity successKilled) {
    this.seckillId = seckillId;
    this.state = seckillStatEnum.getState();
    this.stateInfo = seckillStatEnum.getStateInfo();
    this.successKilled = successKilled;
  }
}
