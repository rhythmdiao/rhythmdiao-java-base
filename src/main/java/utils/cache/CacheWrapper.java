package utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public final class CacheWrapper {
    private Cache cache;

    public CacheWrapper() {
        cache = CacheBuilder.newBuilder().build();
    }

    public CacheWrapper(long maximumSize, long duration, TimeUnit timeUnit) {
        cache = CacheBuilder
                .newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(duration, timeUnit)
                .build();
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }
}
