package com.akai.mapper;

import com.akai.entity.Course;

import java.util.List;

public interface CourseMapper {
    List<Course> findAll();

    int insert(Course course);
}
