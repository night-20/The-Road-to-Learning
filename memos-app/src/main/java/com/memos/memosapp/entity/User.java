package com.memos.memosapp.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data  // 自动生成 getter/setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private LocalDateTime createTime;
}