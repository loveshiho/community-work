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

    @Override
    public void save(List<Course> courses) {
        courses.forEach(course ->  {
            course.setCid(null);    // 自动生成 ID 不使用 Excel中的编号
            courseMapper.insert(course);
        });
    }
}
