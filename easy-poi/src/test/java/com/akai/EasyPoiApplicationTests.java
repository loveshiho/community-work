package com.akai;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.akai.pojo.Card;
import com.akai.pojo.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
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
}
