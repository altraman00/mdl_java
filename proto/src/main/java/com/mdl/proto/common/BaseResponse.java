package com.mdl.proto.common;

import lombok.Data;

/**
 * API接口的基础返回类
 * @param <T>
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


}
