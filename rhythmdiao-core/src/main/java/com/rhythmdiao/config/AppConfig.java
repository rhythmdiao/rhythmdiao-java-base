package com.rhythmdiao.config;

import org.aeonbits.owner.ConfigCache;

import java.util.HashMap;
import java.util.Map;

public final class AppConfig {
    private String env;

    private JettyEnvCfg jettyEnvCfg;

    public JettyEnvCfg getJettyEnvCfg() {
        return jettyEnvCfg;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public final void init() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("env", (env == null || env.isEmpty()) ? "dev" : env);
        jettyEnvCfg = ConfigCache.getOrCreate("jetty_env_config", JettyEnvCfg.class, map);
    }
}
