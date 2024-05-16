package com.akai.controller;

import com.akai.entity.Course;
import com.akai.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<Course> all = courseService.findAll();
        all.forEach(System.out::println);
        model.addAttribute("courses", all);
        return "index";
    }
}
