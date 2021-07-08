package com.mdl.home.exception;

import java.io.Serializable;

/**
 * @Project : java
 * @Package Name : com.mdl.order.exception
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月16日 15:58
 * ----------------- ----------------- -----------------
 */
public class Result implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final int SUCCESS = 200;

  public static final int ERROR = 500;

  /**
   * 状态
   */
  public Integer code;

  /**
   * 数据
   */
  public Object data;

  /**
   * 消息
   */
  public String msg;

  private void xassertNotNull() {
    if (code == null) {
      throw new RuntimeException("code can not be null");
    }
  }

  public Result setData(Object data) {
    this.data = data;
    return this;
  }

  public Integer getCode() {
    return code;
  }

  public Result setCode(Integer code) {
    this.code = code;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public Result setMsg(String msg) {
    this.msg = msg;
    return this;
  }

}
