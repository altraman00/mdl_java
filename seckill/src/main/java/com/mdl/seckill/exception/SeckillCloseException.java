package com.mdl.seckill.exception;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.exception
 * @Author : xiekun
 * @Desc : 秒杀关闭异常
 * @Create Date : 2021年03月07日 17:35
 * ----------------- ----------------- -----------------
 */
public class SeckillCloseException extends SeckillException {

  public SeckillCloseException(String message) {
    super(message);
  }

  public SeckillCloseException(String message, Throwable cause) {
    super(message, cause);
  }

}
