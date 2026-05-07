package com.memos.memosapp.common;

import lombok.Data;

@Data  // Lombok：自动生成 getter/setter/toString
public class Result<T> {

    private int code;       // 状态码：200成功，500失败
    private String message; // 提示信息
    private T data;         // 返回的数据（泛型，什么类型都能装）

    // 成功时调用
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.message = "success";
        result.data = data;
        return result;
    }

    // 失败时调用
    public static Result<?> error(String message) {
        Result<?> result = new Result<>();
        result.code = 500;
        result.message = message;
        result.data = null;
        return result;
    }
}