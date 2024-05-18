package com.akai;

import com.akai.utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

// @SpringBootTest
public class testJwt {
    @Test
    void testGetLoginUserJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJlODQ5ZDU5MjI3NGE0NGE3OTUwNGM4ODZiOTczNGZiOSIsInN1YiI6IjEiLCJpYXQiOjE3MTU5NjY1MDEsImV4cCI6MTcxNTk3MDEwMX0.KK4IsKWU5uHtWcj0zxrdxw0RIofSgnfxxf3bLlqwfvk";
        System.out.println(JwtUtils.parseJwt(jwt));
    }
    @Test
    void testFastJson() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "shiho");
        map.put("password", "12345");
        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString);
        Map parsedObject = JSON.parseObject(jsonString, Map.class);
        System.out.println(parsedObject);
    }
}
