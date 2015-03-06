package utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by mayuxing on 2015/2/13.
 */
public final class CacheWrapper {
    public static <K, V> Cache<K, V> callableCached(final long maximumSize, final long duration, final TimeUnit timeUnit) throws Exception {
        return CacheBuilder
                .newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(duration, timeUnit)
                .build();
    }
}
