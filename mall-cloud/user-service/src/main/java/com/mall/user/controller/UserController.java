package com.mall.user.controller;

import com.mall.common.result.Result;
import com.mall.user.entity.User;
import com.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** 查询用户 GET /api/user/{id} */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    /** 新增用户 POST /api/user  body: { "username":"", "password":"", ... } */
    @PostMapping
    public Result<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    /** 更新用户 PUT /api/user  body: { "id":1, "phone":"新手机号", ... } */
    @PutMapping
    public Result<User> update(@RequestBody User user) {
        return userService.update(user);
    }

    /** 删除用户 DELETE /api/user/{id} */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}