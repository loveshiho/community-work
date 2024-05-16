package com.akai;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.akai.pojo.Card;
import com.akai.pojo.Emp;
import com.akai.pojo.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// @SpringBootTest
class EasyPoiApplicationTests {

    @Test
    void contextLoads() {
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setName("shiho");
            user.setAge(18 + i);
            user.setBirthday(new Date());
            user.setStatus(i % 2 == 0 ? "1" : "0");
            user.setHobby(Arrays.asList("唱", "跳", "rap", "篮球"));
            user.setCard(new Card("0710", "米花町"));
            user.setPhoto("/Users/zhong/Desktop/shiho.jpeg");
            list.add(user);
        }
        return list;
    }

    @Test
    void testExport() throws Exception {
        // 参数列表：1 配置对象 2 导出类型 3 导出数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "测试"), User.class, getUsers());

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhong/Desktop/user.xls");
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }

    @Test
    void testImport() throws Exception {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);  // 列名占几行
        params.setTitleRows(1); // 标题列占几行
        List<Emp> emps = ExcelImportUtil.importExcel(new FileInputStream("/Users/zhong/Desktop/emp.xls"), Emp.class, params);
        emps.forEach(System.out::println);
    }
}
