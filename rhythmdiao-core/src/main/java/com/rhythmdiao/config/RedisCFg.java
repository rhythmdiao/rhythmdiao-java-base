package com.rhythmdiao.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:redis-config.xml"
        , "classpath:redis-config.xml"
        //,
})
public interface RedisCFg extends Config {
    @DefaultValue("dev")
    String env();

    @Key("redis.${env}.host")
    @DefaultValue("localhost")
    String host();

    @Key("redis.${env}.port")
    @DefaultValue("6379")
    int port();
}
