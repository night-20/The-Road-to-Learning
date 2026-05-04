package org.zrl.dome20260505.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zrl.dome20260505.entity.Student;
import org.zrl.dome20260505.mapper.StudentMapper;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/students")
    public List<Student> list() {
        return studentMapper.selectList(null);
    }
}
