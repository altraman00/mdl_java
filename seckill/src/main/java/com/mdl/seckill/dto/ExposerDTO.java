package com.mdl.seckill.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.dto
 * @Author : xiekun
 * @Desc : 暴露秒杀地址DTO
 * @Create Date : 2021年03月07日 17:17
 * ----------------- ----------------- -----------------
 */

@Data
public class ExposerDTO {

  /**
   * 是否开启秒杀
   */
  private boolean exposed;

  /**
   * 对秒杀地址的加密
   */
  private String md5;

  private long seckillId;

  /**
   * 系统时间
   */
  private LocalDateTime now;

  private LocalDateTime start;

  private LocalDateTime end;


  public ExposerDTO(boolean exposed, long seckillId) {
    this.exposed = exposed;
    this.seckillId = seckillId;
  }

  public ExposerDTO(boolean exposed, String md5, long seckillId) {
    this.exposed = exposed;
    this.md5 = md5;
    this.seckillId = seckillId;
  }

  public ExposerDTO(boolean exposed, LocalDateTime now, LocalDateTime start, LocalDateTime end) {
    this.exposed = exposed;
    this.now = now;
    this.start = start;
    this.end = end;
  }

  public ExposerDTO(boolean exposed, long seckillId, LocalDateTime now, LocalDateTime start, LocalDateTime end) {
    this.exposed = exposed;
    this.seckillId = seckillId;
    this.now = now;
    this.start = start;
    this.end = end;
  }

  public ExposerDTO(boolean exposed, String md5, long seckillId, LocalDateTime now, LocalDateTime start, LocalDateTime end) {
    this.exposed = exposed;
    this.md5 = md5;
    this.seckillId = seckillId;
    this.now = now;
    this.start = start;
    this.end = end;
  }



}
