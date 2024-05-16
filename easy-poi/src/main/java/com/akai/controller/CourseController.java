package com.akai.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.akai.entity.Course;
import com.akai.service.CourseService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.net.URLEncoder;
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

    @RequestMapping("/importExcel")
    public String importExcel(MultipartFile excelFile) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<Course> list = ExcelImportUtil.importExcel(excelFile.getInputStream(), Course.class, params);
        courseService.save(list);
        return "index";
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception {
        // 查询所有数据
        List<Course> all = courseService.findAll();
        // 生成 excel
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("课程信息列表", "课程信息"), Course.class, all);
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("课程信息列表.xlsx", "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
