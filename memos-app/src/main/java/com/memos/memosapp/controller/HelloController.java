package com.memos.memosapp.controller;

import com.memos.memosapp.common.Result;
import com.memos.memosapp.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController          // 告诉 Spring：这个类是接口控制器
@RequestMapping("/hello") // 这个类所有接口的前缀都是 /hello
public class HelloController {

    // 访问 GET /hello 时触发
    @GetMapping
    public String hello() {
        return "Hello，码上记！";
    }

    // 访问 GET /hello/张三 时触发，{name} 是动态参数
    @GetMapping("/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello，" + name + "！欢迎来到码上记！";
    }

    // POST 接口：接收 JSON 数据
    // @RequestBody 表示：从请求体里读取 JSON，自动转成 User 对象
    @PostMapping("/user")
    public Result<User> receiveUser(@RequestBody User user) {
        // 假装处理了一下，给 id 赋个值
        user.setId(1L);
        return Result.success(user);
    }
}