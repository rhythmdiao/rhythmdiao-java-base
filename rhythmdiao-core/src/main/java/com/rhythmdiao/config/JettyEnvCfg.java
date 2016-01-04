package com.rhythmdiao.config;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

@HotReload
@LoadPolicy(Config.LoadType.FIRST)
@Sources({"file:jetty-env-config.xml"
        , "classpath:jetty-env-config.xml"
        //,
})
public interface JettyEnvCfg extends Accessible {
    @DefaultValue("dev")
    String env();

    @Key("servers.${env}.connector.host")
    @DefaultValue("localhost")
    String host();

    @Key("servers.${env}.connector.port")
    @DefaultValue("8081")
    int port();

    @Key("servers.${env}.connector.maxIdleTime")
    @DefaultValue("30000")
    int maxIdleTime();

    @Key("servers.${env}.connector.lowResourcesConnections")
    @DefaultValue("5000")
    int lowResourcesConnections();

    @Key("servers.${env}.connector.lowResourcesMaxIdleTime")
    @DefaultValue("5000")
    int lowResourcesMaxIdleTime();

    @Key("servers.${env}.connector.acceptors")
    @DefaultValue("1")
    int acceptors();

    @Key("servers.${env}.connector.statsOn")
    @DefaultValue("false")
    boolean statsOn();

    @Key("servers.${env}.connector.acceptQueueSize")
    @DefaultValue("3")
    int acceptQueueSize();

    @Key("servers.${env}.pool.minThreads")
    @DefaultValue("1")
    int minThreads();

    @Key("servers.${env}.pool.maxThreads")
    @DefaultValue("3")
    int maxThreads();
}
