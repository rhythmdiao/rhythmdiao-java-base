package com.rhythmdiao.utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

@SuppressWarnings("unchecked")
public final class CacheWrapper<K, V> {
    private volatile static CacheWrapper instance;

    private ConcurrentMap<String, Cache<K, V>> cacheMap;

    private CacheWrapper() {
        cacheMap = Maps.newConcurrentMap();
    }

    public static CacheWrapper getInstance() {
        if (instance == null) {
            instance = new CacheWrapper();
        }
        return instance;
    }

    public Cache<K, V> newCache(String cacheName) {
        Cache<K, V> cache = CacheBuilder.newBuilder().build();
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public Cache<K, V> newCache(String cacheName, String config) {
        Cache<K, V> cache = CacheBuilder.from(CacheBuilderSpec.parse(config)).build();
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public Cache<K, V> getOrDefault(String cacheName) {
        Cache<K, V> cache = (Cache<K, V>) getInstance().cacheMap.getOrDefault(cacheName, CacheBuilder.newBuilder().build());
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public Cache<K, V> getOrDefault(String cacheName, String config) {
        Cache<K, V> cache = (Cache<K, V>) getInstance().cacheMap.getOrDefault(cacheName, CacheBuilder.from(CacheBuilderSpec.parse(config)).build());
        putIfAbsent(cacheName, cache);
        return cache;
    }

    private void putIfAbsent(String cacheName, Cache<K, V> cache) {
        getInstance().cacheMap.putIfAbsent(cacheName, cache);
    }
}
