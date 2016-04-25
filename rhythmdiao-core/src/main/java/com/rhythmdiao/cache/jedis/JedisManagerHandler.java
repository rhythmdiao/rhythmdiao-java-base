package com.rhythmdiao.cache.jedis;

import com.google.gson.Gson;
import com.rhythmdiao.cache.HandlerCacheManager;
import redis.clients.jedis.Jedis;

public class JedisManagerHandler implements HandlerCacheManager {
    private static JedisManagerHandler instance = new JedisManagerHandler();

    public static JedisManagerHandler getInstance() {
        return instance;
    }

    private JedisManagerHandler() {
    }

    @Override
    public void set(String key, Object value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.INSTANCE.getJedis();
            Gson gson = new Gson();
            if (seconds > 0) {
                jedis.setex(key, seconds, gson.toJson(value));
            } else {
                jedis.set(key, gson.toJson(value));
            }
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.INSTANCE.getJedis();
            Gson gson = new Gson();
            Object result = gson.fromJson(jedis.get(key), Object.class);
            jedis.close();
            return result;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public <T> T get(String key, Class<T> cls) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.INSTANCE.getJedis();
            Gson gson = new Gson();
            T result = gson.fromJson(jedis.get(key), cls);
            jedis.close();
            return result;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.INSTANCE.getJedis();
            jedis.del(key);
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
}
