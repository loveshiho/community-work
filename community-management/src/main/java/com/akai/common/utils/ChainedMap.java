package com.akai.common.utils;

import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.HashMap;

public class ChainedMap extends LinkedCaseInsensitiveMap {
    public ChainedMap() {
        super();
    }

    /*
     * 创建ChainedMap
     * */
    public static ChainedMap create() {
        return new ChainedMap();
    }

    public static <K, V> HashMap<K, V> newMap() {
        return new HashMap<>(16);
    }

    /*
     * 设置列
     * */
    public ChainedMap set(String attr, Object val) {
        this.put(attr, val);
        return this;
    }

    /*
     * 设置列，当键或值为null时忽略
     * */
    public ChainedMap setIgnoreNull(String attr, Object val) {
        if (val != null && attr != null) {
            this.set(attr, val);
        }
        return this;
    }

    public Object get(String key) {
        return super.get(key);
    }

    public <T> T getOrDefault(String key, T defaultValue) {
        Object res = get(key);
        return (T) (res == null ? defaultValue : res);
    }
}
