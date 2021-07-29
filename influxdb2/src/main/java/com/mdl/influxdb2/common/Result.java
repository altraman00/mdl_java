package com.mdl.influxdb2.common;

import java.io.Serializable;

public class Result implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 成功
   */
  public static final Integer SUCCESS = 200;
  /**
   * 警告
   */
  public static final Integer WARN = -1;
  /**
   * 异常 失败
   */
  public static final Integer FAIL = 500;

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
