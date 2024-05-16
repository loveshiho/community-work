package com.akai;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.akai.pojo.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

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

    /*多sheet页导入*/
    public <T> List<T> importMultiSheet(String filePath, int sheetIndex, int titleRows, int headerRows, Class<T> pojoClass) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setStartSheetIndex(sheetIndex);  // sheet页下标

        // 表示表头必须包含的字段,不包含 就报错
        params.setImportFields(new String[]{"用户ID"});

        List<T> list = ExcelImportUtil.importExcel(new FileInputStream(filePath), pojoClass, params);
        return list;
    }

    @Test
    void testSheet() throws Exception {
        String filePath = "/Users/zhong/Desktop/login.xlsx";
        Class<LoginUrl> loginUrlClass = LoginUrl.class;
        Class<LoginUser> loginUserClass = LoginUser.class;
        // sheetIndex从0开始，0代表第一页
        List<LoginUrl> list = importMultiSheet(filePath, 1, 1, 1, loginUrlClass);
        list.forEach(System.out::println);
    }

    public void exportMultiSheet(Object... objects) throws Exception {
        /*map1*/
        // 创建参数对象,用于设定 Excel的 sheet页内容等信息
        ExportParams loginUserParams = new ExportParams();
        // 设置 sheet的名称
        loginUserParams.setSheetName("登录用户");
        loginUserParams.setTitle("登录用户列表");

        // 使用 map创建 sheet1
        HashMap<String, Object> map1 = new HashMap<>();
        // 设置title
        map1.put("title", loginUserParams);
        // 设置导出的实体类型
        map1.put("entity", LoginUser.class);
        // sheet中要填充的数据
        map1.put("data", objects[0]);

        /*map2*/
        // 创建参数对象,用于设定 Excel的 sheet页内容等信息
        ExportParams loginUrlParams = new ExportParams();
        // 设置 sheet的名称
        loginUrlParams.setSheetName("URL路径");
        loginUrlParams.setTitle("URL路径");

        // 使用 map创建 sheet1
        HashMap<String, Object> map2 = new HashMap<>();
        // 设置title
        map2.put("title", loginUrlParams);
        // 设置导出的实体类型
        map2.put("entity", LoginUrl.class);
        // sheet中要填充的数据
        map2.put("data", objects[1]);

        // 将sheet1和 sheet2进行包装
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        // 执行导出方法
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        workbook.write(new FileOutputStream("/Users/zhong/Desktop/exportLogin.xls"));
        workbook.close();
    }

    /*测试导出多sheet页*/
    @Test
    void testExportSheet() throws Exception {
        List<LoginUser> loginUsers = new ArrayList<>();
        loginUsers.add(new LoginUser(1, "conan", "123456", new Date(), "vip"));
        loginUsers.add(new LoginUser(2, "shiho", "123456", new Date(), "vip"));
        loginUsers.add(new LoginUser(3, "haibara", "123456", new Date(), "vip"));

        List<LoginUrl> loginUrls = new ArrayList<>();
        loginUrls.add(new LoginUrl(1, "post", "www.loveshiho.com"));
        loginUrls.add(new LoginUrl(2, "get", "www.loveshiho.cn"));

        exportMultiSheet(loginUsers, loginUrls);
    }
}
