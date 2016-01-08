package com.rhythmdiao.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:authority-config.xml"
        , "classpath:authority.xml"
        //,
})
public interface AuthorityCfg extends Config {
    @DefaultValue("dev")
    String env();

    @Key("authorities.${env}.appKey.android")
    @DefaultValue("")
    String androidKey();

    @Key("authorities.${env}.appKey.ios")
    @DefaultValue("")
    String iosKey();

    @Key("authorities.${env}.appKey.web")
    @DefaultValue("")
    String webAppKey();

    @Key("authorities.${env}.appKey.other")
    @DefaultValue("")
    String otherKey();
}
