package com.rhythmdiao.cache;

import com.google.common.collect.MapMaker;
import com.rhythmdiao.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;

public class HandlerCacheFactory {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerCacheFactory.class);
    private HandlerCacheManager firstCache;

    //TODO
    private HandlerCacheManager secondCache;

    private ConcurrentMap<String, HandlerCache> HANDLER_CACHE_MAP;

    private static HandlerCacheFactory instance = new HandlerCacheFactory();

    public static HandlerCacheFactory getInstance() {
        return instance;
    }

    private HandlerCacheFactory() {
        HANDLER_CACHE_MAP = new MapMaker().makeMap();
    }

    public HandlerCache getHandlerCache(final String key) {
        HandlerCache handlerCache = HANDLER_CACHE_MAP.get(key);
        if (handlerCache == null) {
            synchronized (key) {
                handlerCache = HANDLER_CACHE_MAP.get(key);
                if (handlerCache == null) {
                    handlerCache = new HandlerCache(key);
                    handlerCache.setFirstCache(firstCache);
                    HANDLER_CACHE_MAP.put(key, handlerCache);
                    LogUtil.info(LOG, "add a new handler cache, and the key is {}", key);
                }
            }
        }
        return handlerCache;
    }

    public void setFirstCache(HandlerCacheManager firstCache) {
        this.firstCache = firstCache;
    }

    public HandlerCacheManager getFirstCache() {
        return firstCache;
    }

    public HandlerCacheManager getSecondCache() {
        throw new UnsupportedOperationException("unsupported second cache");
    }

    public void setSecondCache(HandlerCacheManager secondCache) {
        throw new UnsupportedOperationException("unsupported second cache");
    }
}
