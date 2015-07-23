package com.rhythmdiao.utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public final class CacheWrapper<K, V> {
    private static CacheWrapper instance = new CacheWrapper();

    private ConcurrentMap<String, Cache<K, V>> cacheMap;

    private CacheWrapper() {
        cacheMap = new MapMaker().makeMap();
    }

    public static CacheWrapper getInstance() {
        return instance;
    }

    public Cache<K, V> newCache(String cacheName) {
        Cache<K, V> cache = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).softValues().build();
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public Cache<K, V> newCache(String cacheName, String config) {
        Cache<K, V> cache = CacheBuilder.from(CacheBuilderSpec.parse(config)).build();
        putIfAbsent(cacheName, cache);
        return cache;
    }

    public Cache<K, V> getOrDefault(String cacheName) {
        Cache<K, V> cache = (Cache<K, V>) getInstance().cacheMap.getOrDefault(cacheName, CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).softValues().build());
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
