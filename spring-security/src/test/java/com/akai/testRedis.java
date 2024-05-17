package com.akai;

import com.akai.pojo.LoginUser;
import com.akai.pojo.User;
import com.akai.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testRedis {
    @Autowired
    private RedisCache redisCache;

    @Test
    void testSet() {
        /*RedisCache redis = new RedisCache();*/
        final String key = "security:user:shiho";
        final User user = new User("shiho", "123456");
        redisCache.setCacheObject(key, user);
    }

    @Test
    void testGet() {
        final String key = "security:user:shiho";
        User user = redisCache.getCacheObject(key, User.class);
        System.out.println(user);
        /*User{username='shiho', password='123456'}*/
    }
}
