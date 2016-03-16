package com.rhythmdiao.util;

import com.rhythmdiao.util.time.TimeMonitor;

/**
 * Created by mayuxing on 2016/1/29.
 */
public enum TimeUtil {
    INSTANCE;

    public TimeMonitor getMoniter(){
        return new TimeMonitor();
    }
}
