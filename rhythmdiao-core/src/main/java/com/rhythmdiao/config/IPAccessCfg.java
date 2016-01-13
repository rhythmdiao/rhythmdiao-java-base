package com.rhythmdiao.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.HotReloadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Reloadable;

import java.util.concurrent.TimeUnit;

@HotReload(value = 30, unit = TimeUnit.MINUTES, type = HotReloadType.SYNC)
@Sources({"file:ip-access-config.xml"
        , "classpath:ip-access-config.xml"
        //,
})
public interface IPAccessCfg extends Config, Reloadable {
    @DefaultValue("dev")
    String env();

    @Config.Key("access.${env}.ip")
    @DefaultValue("*")
    String[] forbidden();
}
