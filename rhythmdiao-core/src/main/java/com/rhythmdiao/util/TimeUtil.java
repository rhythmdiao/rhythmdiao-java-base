package com.rhythmdiao.util;

import com.rhythmdiao.util.time.TimeMonitor;

public enum TimeUtil {
    INSTANCE;

    public TimeMonitor getMonitor(){
        return new TimeMonitor();
    }
}
