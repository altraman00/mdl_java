package com.mdl.influxdb2.common;

/**
 * @Project : influxdb2
 * @Author : xiekun 提供统一的REST相应对象封装  用于提供返回对象的封装
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

import lombok.Data;

/**
 * API接口的基础返回类
 */
@Data
public class BaseResponse<T> {

  /**
   * 是否成功
   */
  private int code;

  /**
   * 说明
   */
  private String msg;

  /**
   * 返回数据
   */
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


  /**
   * 失败并返回数据
   */
  public static BaseResponse error(Object data) {
    return new BaseResponse(ResultCode.ERROR, data);
  }

  public static BaseResponse error() {
    return new BaseResponse(ResultCode.ERROR);
  }

  /**
   * 警告并返回数据
   */
  public static BaseResponse warn(Object data) {
    return new BaseResponse(ResultCode.WARN, data);
  }

  /**
   * 成功并返回数据
   */
  public static BaseResponse ok(String msg, Object data) {
    return new BaseResponse(ResultCode.SUCCESS, data);
  }

  /**
   * 带数据
   */
  public static BaseResponse ok(Object data) {
    return new BaseResponse(ResultCode.SUCCESS, data);
  }

  /**
   * 成功返回通用数据
   */
  public static BaseResponse ok() {
    return new BaseResponse(ResultCode.SUCCESS);
  }




}
