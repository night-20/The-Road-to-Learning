package org.zrl.dome20260505.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("students")
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
}
