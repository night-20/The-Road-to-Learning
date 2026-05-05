package com.mall.user.service;

import com.mall.common.result.Result;
import com.mall.user.entity.User;

/**
 * 用户业务逻辑接口
 */
public interface UserService {

    /** 根据ID查询用户 */
    Result<User> getById(Long id);

    /** 新增用户（注册） */
    Result<User> create(User user);

    /** 更新用户信息 */
    Result<User> update(User user);

    /** 删除用户（逻辑删） */
    Result<Void> delete(Long id);
}