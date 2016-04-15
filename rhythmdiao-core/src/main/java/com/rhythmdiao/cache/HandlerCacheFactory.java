package com.rhythmdiao.cache;

import com.rhythmdiao.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class HandlerCacheFactory {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerCacheFactory.class);
    private HandlerCacheManager firstCache;

    //TODO
    private HandlerCacheManager secondCache;

    private static final ConcurrentHashMap<String, HandlerCache> HANDLER_CACHE_MAP = new ConcurrentHashMap<String, HandlerCache>(1000);

    private static HandlerCacheFactory instance = new HandlerCacheFactory();

    public static HandlerCacheFactory getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (HandlerCacheFactory.class) {
            if (instance == null) {
                instance = new HandlerCacheFactory();
            }
        }
        return instance;
    }

    public HandlerCache getHandlerCache(String key) {
        HandlerCache handlerCache = HANDLER_CACHE_MAP.get(key);
        System.out.println(key);
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

    private HandlerCacheFactory() {
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
