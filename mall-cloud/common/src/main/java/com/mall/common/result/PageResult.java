package com.mall.common.result;

import lombok.Data;

import java.util.List;

/**
 * 分页返回结果 — 列表查询统一用这个
 * <p>
 * 前端收到的 JSON：
 * {
 *   "total": 100,
 *   "page": 1,
 *   "size": 10,
 *   "records": [ ... ]
 * }
 */
@Data
public class PageResult<T> {

    /** 总条数 */
    private long total;
    /** 当前页码 */
    private long page;
    /** 每页条数 */
    private long size;
    /** 当前页数据 */
    private List<T> records;

    public static <T> PageResult<T> of(long total, long page, long size, List<T> records) {
        PageResult<T> result = new PageResult<>();
        result.total = total;
        result.page = page;
        result.size = size;
        result.records = records;
        return result;
    }
}