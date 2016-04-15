package com.rhythmdiao.cache;

public interface HandlerCacheManager {
    void set(String key, Object value, int seconds);

    Object get(String key);

    <T> T get(String key, Class<T> cls);

    void delete(String key);
}
