package com.rhythmdiao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerCache {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerCache.class);

    private String key;

    private HandlerCacheManager firstCache;

    //TODO
    private HandlerCacheManager secondCache;

    public HandlerCache(String key) {
        this.key = key;
    }

    public Object get() {
        return firstCache.get(key);
    }

    public <T> T get(Class<T> type) {
        return firstCache.get(key, type);
    }

    public void set(Object value, int seconds) {
        firstCache.set(key, value, seconds);
    }

    public void delete() {
        firstCache.delete(key);
    }

    public HandlerCacheManager getFirstCache() {
        return firstCache;
    }

    public void setFirstCache(HandlerCacheManager firstCache) {
        this.firstCache = firstCache;
    }
}
