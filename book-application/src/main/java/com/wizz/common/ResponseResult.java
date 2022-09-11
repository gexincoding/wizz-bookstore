package com.wizz.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public static <T> ResponseResult<T> success(T object){
        ResponseResult<T> r = new ResponseResult<>();
        r.code = 1;
        r.data = object;
        return r;
    }

    public static <T> ResponseResult<T> error(String message){
        ResponseResult<T> r = new ResponseResult<>();
        r.code = 0;
        r.msg = message;
        return r;
    }
}
