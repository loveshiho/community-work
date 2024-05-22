package com.akai.redis;

import com.akai.common.utils.RedisCache;
import com.akai.system.domain.SysDictData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisCache redisCache;

    @Test
    void testSetList() {
        List<String> list = Arrays.asList("1", "2", "3");
        redisCache.setCacheObject("test:list", list);
    }

    @Test
    void testGetList() {
        List<SysDictData> cacheList = redisCache.getCacheList("sys_dict:sys_user_sex", SysDictData.class);
        cacheList.forEach(System.out::println);
    }
}
