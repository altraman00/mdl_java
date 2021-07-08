package com.mdl.seckill.exception;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.exception
 * @Author : xiekun
 * @Desc : 重复秒杀异常（运行期异常）
 * @Create Date : 2021年03月07日 17:33
 * ----------------- ----------------- -----------------
 */
public class SeckillRepeatException extends SeckillException{

  public SeckillRepeatException(String message) {
    super(message);
  }

  public SeckillRepeatException(String message, Throwable cause) {
    super(message, cause);
  }

}
