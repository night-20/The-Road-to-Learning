package com.mall.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户实体 — 对应数据库 t_user 表
 */
@Data
@TableName("t_user")          // 声明表名
public class User {

    @TableId(type = IdType.AUTO)          // 主键自增
    private Long id;

    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;

    @TableField(fill = FieldFill.INSERT)  // 插入时自动填充
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入和更新时自动填充
    private LocalDateTime updateTime;

    @TableLogic                          // 逻辑删除字段（MyBatis-Plus 自动拼接 WHERE deleted=0）
    private Integer deleted;
}