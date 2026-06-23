package com.erp.erp_system.common;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T>Result<T>success(T data){
        Result<T>result=new Result<>();
        result.code=200;
        result.message="success";
        result.data=data;
        return result;
    }

    public static Result<?>fail(String message){
            Result<?>result=new Result<>();
    result.code=500;
    result.message=message;
    return result;
    }

}