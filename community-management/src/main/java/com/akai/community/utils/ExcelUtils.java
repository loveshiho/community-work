package com.akai.community.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtils {
    /*excel导出*/
    public static void exportExcel(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams params) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(params, pojoClass, list);
        downLoadExcel(fileName, response, workbook);
    }

    /*excel下载*/
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }
}
