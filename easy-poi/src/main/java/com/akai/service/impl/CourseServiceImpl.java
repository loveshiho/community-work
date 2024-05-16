package com.akai.service.impl;

import com.akai.entity.Course;
import com.akai.mapper.CourseMapper;
import com.akai.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findAll() {
        List<Course> all = courseMapper.findAll();
        return all;
    }
}
