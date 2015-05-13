package utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public final class CacheWrapper {
    private static CacheWrapper instance = null;

    private ConcurrentMap<String, Cache> cacheMap;

    private CacheWrapper() {
        cacheMap = Maps.newConcurrentMap();
    }

    public synchronized static CacheWrapper getInstance() {
        if (instance == null) {
            instance = new CacheWrapper();
        }
        return instance;
    }

    public static synchronized Cache newCache(String cacheName) {
        Cache cache = CacheBuilder.newBuilder().build();
        getInstance().cacheMap.putIfAbsent(cacheName, cache);
        return cache;
    }

    public static synchronized Cache newCacheWithParameter(String cacheName, long maximumSize, long duration, TimeUnit timeUnit) {
        Cache cache = CacheBuilder
                .newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(duration, timeUnit)
                .build();
        getInstance().cacheMap.putIfAbsent(cacheName, cache);
        return cache;
    }

    public static synchronized Cache getCache(String cacheName) {
        Cache cache = getInstance().cacheMap.getOrDefault(cacheName, CacheBuilder.newBuilder().build());
        getInstance().cacheMap.putIfAbsent(cacheName, cache);
        return cache;
    }

    public static synchronized Cache getCacheWithParameter(String cacheName, long maximumSize, long duration, TimeUnit timeUnit) {
        Cache cache = getInstance().cacheMap.getOrDefault(cacheName, CacheBuilder
                .newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(duration, timeUnit)
                .build());
        getInstance().cacheMap.putIfAbsent(cacheName, cache);
        return cache;
    }
}
