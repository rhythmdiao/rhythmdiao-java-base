package com.rhythmdiao.cache;

import com.rhythmdiao.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HandlerCacheFactory {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerCacheFactory.class);

    private HandlerCacheManager firstCache;
    private HandlerCacheManager secondCache;

    private ConcurrentMap<String, HandlerCache> HANDLER_CACHE_MAP;

    private static HandlerCacheFactory instance = new HandlerCacheFactory();

    public static HandlerCacheFactory getInstance() {
        return instance;
    }

    private HandlerCacheFactory() {
        HANDLER_CACHE_MAP = new ConcurrentHashMap<>(100000);
    }

    public HandlerCache getHandlerCache(final String key) {
        HandlerCache handlerCache = HANDLER_CACHE_MAP.get(key);
        if (handlerCache != null) return handlerCache;

        synchronized (key) {
            handlerCache = HANDLER_CACHE_MAP.get(key);
            if (handlerCache != null) return handlerCache;

            handlerCache = new HandlerCache(key);
            handlerCache.setFirstCache(firstCache);
            handlerCache.setSecondCache(secondCache);
            HANDLER_CACHE_MAP.put(key, handlerCache);
            LogUtil.info(LOG, "add a new handler cache, and the key is {}", key);
            return handlerCache;
        }
    }

    public void setFirstCache(HandlerCacheManager firstCache) {
        this.firstCache = firstCache;
    }

    public HandlerCacheManager getFirstCache() {
        return firstCache;
    }

    public HandlerCacheManager getSecondCache() {
        return secondCache;
    }

    public void setSecondCache(HandlerCacheManager secondCache) {
        this.secondCache = secondCache;
    }
}
