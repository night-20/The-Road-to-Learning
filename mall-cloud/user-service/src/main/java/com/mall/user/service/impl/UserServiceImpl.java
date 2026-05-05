package com.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.common.result.Result;
import com.mall.user.entity.User;
import com.mall.user.mapper.UserMapper;
import com.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 用户业务逻辑实现
 */
@Service
@RequiredArgsConstructor          // Lombok：生成带 final 和 @NonNull 字段的构造器，用于注入
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;   // 构造器注入

    @Override
    public Result<User> getById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.notFound("用户不存在");
        }
        // 返回前把密码洗掉，不暴露到前端
        user.setPassword(null);
        return Result.ok(user);
    }

    @Override
    public Result<User> create(User user) {
        // 1. 查用户名是否已存在
        Long count = userMapper.selectCount(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, user.getUsername())
        );
        if (count > 0) {
            return Result.badRequest("用户名已存在");
        }

        // 2. 密码做 MD5 加密（生产环境要用 BCrypt）
        String encryptedPassword = DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)
        );
        user.setPassword(encryptedPassword);

        // 3. 插入数据库
        userMapper.insert(user);
        // insert 后 MyBatis-Plus 会自动回填主键到 user.id

        // 4. 返回时洗掉密码
        user.setPassword(null);
        return Result.ok(user);
    }

    @Override
    public Result<User> update(User user) {
        // 1. 先查是否存在
        User dbUser = userMapper.selectById(user.getId());
        if (dbUser == null) {
            return Result.notFound("用户不存在");
        }

        // 2. 如果传了新密码就加密；否则保留原密码
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(
                    DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8))
            );
        } else {
            user.setPassword(null); // 不更新密码字段
        }

        // 3. 更新
        userMapper.updateById(user);
        // 注意：updateById 只更新非 null 字段，所以密码为 null 时不会被覆盖

        // 4. 查最新数据返回
        return getById(user.getId());
    }

    @Override
    public Result<Void> delete(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.notFound("用户不存在");
        }
        userMapper.deleteById(id);   // MyBatis-Plus 自动转成 UPDATE ... SET deleted=1
        return Result.ok();
    }
}