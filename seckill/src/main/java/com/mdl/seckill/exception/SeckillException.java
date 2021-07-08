package com.mdl.seckill.exception;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.seckill.exception
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年03月07日 17:36
 * ----------------- ----------------- -----------------
 */
public class SeckillException extends RuntimeException {

  public SeckillException(String message) {
    super(message);
  }

  public SeckillException(String message, Throwable cause) {
    super(message, cause);
  }
}
