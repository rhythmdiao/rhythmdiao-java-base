package utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentMap;

public final class CacheWrapper{
    private static CacheWrapper instance = null;

    private ConcurrentMap<String, Cache<String, Object>> cacheMap;

    private CacheWrapper() {
        cacheMap = new MapMaker().makeMap();
    }

    public synchronized static CacheWrapper getInstance() {
        if (instance == null) {
            instance = new CacheWrapper();
        }
        return instance;
    }

    public static synchronized Cache<String, Object> newCache(String cacheName) {
        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public static synchronized Cache<String, Object> newCacheWithConfig(String cacheName, String config) {
        Cache<String, Object> cache = CacheBuilder.from(CacheBuilderSpec.parse(config)).build();
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public static synchronized Cache<String, Object> getOrDefault(String cacheName) {
        Cache<String, Object> cache = getInstance().cacheMap.getOrDefault(cacheName, CacheBuilder.newBuilder().<String, Object>build());
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public static synchronized Cache<String, Object> getOrDefaultWithConfig(String cacheName, String config) {
        Cache<String, Object> cache = getInstance().cacheMap.getOrDefault(cacheName, CacheBuilder.from(CacheBuilderSpec.parse(config)).<String, Object>build());
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public static synchronized void putIfAbsent(String cacheName, Cache<String, Object> cache) {
        getInstance().cacheMap.putIfAbsent(cacheName, cache);
    }
}
