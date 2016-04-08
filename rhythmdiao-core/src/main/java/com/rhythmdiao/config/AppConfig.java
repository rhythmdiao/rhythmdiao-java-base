package com.rhythmdiao.config;

import org.aeonbits.owner.ConfigCache;

import java.util.HashMap;
import java.util.Map;

public final class AppConfig {
    private String env = "dev";

    private JettyEnvCfg jettyEnvCfg;

    private AuthorityCfg authorityCfg;

    private IPAccessCfg ipAccessCfg;

    private RedisCFg redisCFg;

    public JettyEnvCfg getJettyEnvCfg() {
        return jettyEnvCfg;
    }

    public AuthorityCfg getAuthorityCfg() {
        return authorityCfg;
    }

    public IPAccessCfg getIpAccessCfg() {
        return ipAccessCfg;
    }

    public RedisCFg getRedisCFg() {
        return redisCFg;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public final void init() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("env", env);
        jettyEnvCfg = ConfigCache.getOrCreate("jetty_env_config", JettyEnvCfg.class, map);
        authorityCfg = ConfigCache.getOrCreate("authority_config", AuthorityCfg.class, map);
        ipAccessCfg = ConfigCache.getOrCreate("ip_access_config", IPAccessCfg.class, map);
        redisCFg = ConfigCache.getOrCreate("redis_config", RedisCFg.class, map);
    }
}
