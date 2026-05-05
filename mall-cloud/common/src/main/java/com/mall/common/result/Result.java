package com.mall.common.result;

import lombok.Data;

/**
 * 统一返回结果 — 所有接口都用这个包装
 * <p>
 * 前端收到的 JSON 永远是这样的：
 * {
 *   "code": 200,
 *   "message": "success",
 *   "data": { ... }
 * }
 */
@Data
public class Result<T> {

    /** 状态码（200成功，其它失败） */
    private int code;
    /** 提示信息 */
    private String message;
    /** 返回数据，泛型适配各种类型 */
    private T data;

    // ========== 私有构造器，强制通过静态方法创建 ==========
    private Result() {}

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // ========== 成功：有数据 / 无数据 ==========
    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    // ========== 失败：自定义状态码和消息 ==========
    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message, null);
    }

    // ========== 常用失败快捷方法 ==========
    public static <T> Result<T> fail(String message) {
        return fail(500, message);
    }

    public static <T> Result<T> badRequest(String message) {
        return fail(400, message);
    }

    public static <T> Result<T> notFound(String message) {
        return fail(404, message);
    }
}