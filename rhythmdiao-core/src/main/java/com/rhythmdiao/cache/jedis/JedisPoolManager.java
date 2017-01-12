package com.rhythmdiao.cache.jedis;

import com.rhythmdiao.util.RetryUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

enum JedisPoolManager {
    INSTANCE;

    private Map<String, JedisPool> pools = new HashMap<String, JedisPool>();

    private JedisPool defaultPool;

    JedisPoolManager() {
            /*String host = cfg.host();
            int port = cfg.port();
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMinIdle(cfg.minIdle());
            config.setMaxIdle(cfg.maxIdle());
            config.setMaxWaitMillis(cfg.maxWaitMillis());*/
        defaultPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
        pools.put("127.0.0.1:6379", defaultPool);
    }

    private JedisPool getPool(String host, int port) {
        final String key = host + ":" + port;
        JedisPool pool;
        if (pools.containsKey(key)) {
            pool = pools.get(key);
        } else {
            pool = new JedisPool(host, port);
            pools.put(key, pool);
        }
        return pool;
    }

    public Jedis getJedis(final String host, final int port) {
        final Jedis[] jedis = new Jedis[1];
        boolean got = new RetryUtil() {
            @Override
            public Boolean execute() {
                try {
                    JedisPool pool = getPool(host, port);
                    jedis[0] = pool.getResource();
                    return true;
                } catch (Exception ignored) {
                    return false;
                }
            }
        }.isDone();

        return got ? jedis[0] : null;
    }

    public Jedis getJedis() {
        final Jedis[] jedis = new Jedis[1];
        boolean got = new RetryUtil() {
            @Override
            public Boolean execute() {
                try {
                    jedis[0] = defaultPool.getResource();
                    return true;
                } catch (Exception ignored) {
                    return false;
                }
            }
        }.isDone();

        return got ? jedis[0] : null;
    }

    public boolean close(String host, int port) {
        final String key = host + ":" + port;
        if (pools.containsKey(key) && !pools.get(key).isClosed()) {
            pools.get(key).close();
            pools.remove(key);
            return true;
        }
        return false;
    }

    public boolean destory(String host, int port) {
        return this.close(host, port);
    }
}
