package com.rhythmdiao.cache;

import com.rhythmdiao.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class HandlerCache {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerCache.class);

    private String key;

    private HandlerCacheManager firstCache;
    private HandlerCacheManager secondCache;

    /**
     * 一级缓存访问锁
     */
    private AtomicBoolean firstCacheLock = new AtomicBoolean(false);

    public HandlerCache(String key) {
        this.key = key;
    }

    /**
     * 访问或者修改接口缓存
     *
     * @return 接口缓存结果
     */
    public Object getAndSet(Object value, int seconds) {
        Object result = firstCache.get(key);
        if (result != null) return result;
        LogUtil.debug(LOG, "the first cache contains no value,the lock remains {}", firstCacheLock.get());
        //get first cache lock
        if (firstCacheLock.compareAndSet(false, true)) {
            try {
                result = firstCache.get(key);
                if (result == null) {
                    firstCache.set(key, value, seconds);
                } else {

                }
            } finally {
                firstCacheLock.set(false);
            }

        } //get value from second cache
        else {
            result = secondCache.get(key);
            if (result == null) {
                secondCache.set(key, value, seconds);
                result = secondCache.get(key);
            }
        }
        return result;
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

    public HandlerCacheManager getSecondCache() {
        return secondCache;
    }

    public void setSecondCache(HandlerCacheManager secondCache) {
        this.secondCache = secondCache;
    }
}
