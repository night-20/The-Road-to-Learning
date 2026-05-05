package com.mall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层 — 继承 BaseMapper 后，自动拥有 CRUD 方法
 *
 * 常用方法（不用写一行 SQL）：
 *   insert(user)       → 插入
 *   deleteById(id)     → 按主键删除
 *   updateById(user)   → 按主键更新
 *   selectById(id)     → 按主键查
 *   selectList(wrapper)→ 条件查询
 *   selectPage(...)    → 分页查询
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 基础的 CRUD 全由 BaseMapper 提供，这里什么都不用写
}