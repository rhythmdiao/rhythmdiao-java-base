package com.rhythmdiao.jedis;

import com.rhythmdiao.config.RedisCFg;
import org.aeonbits.owner.ConfigCache;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public enum JedisManager {
    INSTANCE;

    private Map<String, JedisPool> poolMap = new HashMap<String, JedisPool>();

    JedisManager() {
        RedisCFg cfg = ConfigCache.get("redis_config");
        if (cfg != null) {
            String host = cfg.host();
            int port = cfg.port();
            JedisPool defaultPool = new JedisPool(cfg.host(), cfg.port());
            poolMap.put(host + ":" + port, defaultPool);
        }
    }

    public JedisPool getPool(String host, String port) {
        return poolMap.get(host + ":" + port);
    }
}
