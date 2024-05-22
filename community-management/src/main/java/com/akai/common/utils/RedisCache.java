package com.akai.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String toJson(Object o) {
        return JSON.toJSONString(o);
    }

    public <T> T parseJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public <T> List<T> parseJsonToList(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) return null;
        List<T> list = JSON.parseArray(json, clazz);
        return list;
    }

    /*缓存基本对象*/
    public <T> void setCacheObject(final String key, final T value) {
        String toJsonValue = toJson(value);
        stringRedisTemplate.opsForValue().set(key, toJsonValue);
    }

    /*缓存list集合*/
    public <T> void setCacheList(final String key, final List<T> value) {
        String json = toJson(value);
        stringRedisTemplate.opsForValue().set(key, json);
    }

    /*拿出基本对象*/
    public <T> T getCacheObject(final String key, Class<T> clazz) {
        String rawObject = stringRedisTemplate.opsForValue().get(key);
        return parseJson(rawObject, clazz);
    }

    /*缓存基本对象，设置过期时间*/
    /*
     * timeout 时间
     * timeUnit 时间颗粒度 TimeUnit枚举类
     * */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        String toJsonValue = toJson(value);
        stringRedisTemplate.opsForValue().set(key, toJsonValue, timeout, timeUnit);
    }

    public Boolean delCacheObject(final String key) {
        return stringRedisTemplate.delete(key);
    }

    public Boolean deleteObject(String redisKey) {
        return stringRedisTemplate.delete(redisKey);
    }

    public long deleteObject(Collection<String> keys) {
        return stringRedisTemplate.delete(keys);
    }

    /*取出list集合*/
    public <T> List<T> getCacheList(final String key, Class<T> clazz) {
        String json = stringRedisTemplate.opsForValue().get(key);
        return parseJsonToList(json, clazz);
    }

    /*获取所有键*/
    public Collection<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }
}
