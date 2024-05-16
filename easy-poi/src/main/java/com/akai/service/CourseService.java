package com.akai.service;

import com.akai.entity.Course;

import java.util.List;

public interface CourseService {
    // 查询所有
    List<Course> findAll();
    // 插入数据
    void save(List<Course> courses);
}
