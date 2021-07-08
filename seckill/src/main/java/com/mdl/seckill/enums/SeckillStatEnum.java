package com.mdl.seckill.enums;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.enums
 * @Author : xiekun
 * @Desc : 使用枚举表述常量数据字段
 * @Create Date : 2021年03月07日 20:38
 * ----------------- ----------------- -----------------
 */

public enum SeckillStatEnum {

  DATA_REWRITE(-3, "数据被篡改"),
  REPEAT_KILL(-2, "重复秒杀"),
  ERROR(-1, "系统内部错误"),
  END(0, "秒杀结束"),
  SUCCESS(1, "秒杀成功");

  private int state;

  private String stateInfo;


  SeckillStatEnum(int state, String stateInfo) {
    this.state = state;
    this.stateInfo = stateInfo;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getStateInfo() {
    return stateInfo;
  }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

  public static SeckillStatEnum stateOf(int index) {
    for (SeckillStatEnum state : values()) {
      if (state.getState() == index) {
        return state;
      }
    }
    return ERROR;
  }

}
