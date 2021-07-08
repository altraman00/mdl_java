package com.mdl.home.exception;

import lombok.Data;

/**
 * @Project : java
 * @Package Name : com.mdl.order.exception
 * @Description : 提供统一的REST相应对象封装  用于提供返回对象的封装
 * @Author : xiekun
 * @Create Date : 2020年09月16日 15:58
 * ----------------- ----------------- -----------------
 */

@Data
public class BaseResponse<T> {

  private int code;

  private String msg;

  private T data;

  public BaseResponse() {

  }

  public BaseResponse(ResultCode res) {
    this.code = res.getCode();
    this.msg = res.getMsg();
  }

  public BaseResponse(ResultCode res, T data) {
    this.code = res.getCode();
    this.msg = res.getMsg();
    this.data = data;
  }

  public BaseResponse(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public BaseResponse(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}
