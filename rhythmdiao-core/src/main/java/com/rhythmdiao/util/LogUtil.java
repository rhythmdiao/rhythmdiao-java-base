package com.rhythmdiao.util;

import org.slf4j.Logger;

public enum LogUtil {
    ;

    public static void info(Logger LOG, String msg, Object... objs) {
        if (LOG.isInfoEnabled()) LOG.info(msg, objs);
    }

    public static void debug(Logger LOG, String msg, Object... objs) {
        if (LOG.isDebugEnabled()) LOG.debug(msg, objs);
    }

    public static void warn(Logger LOG, String msg, Object... objs) {
        if (LOG.isWarnEnabled()) LOG.warn(msg, objs);
    }

    public static void error(Logger LOG, String msg, Object... objs) {
        if (LOG.isErrorEnabled()) LOG.error(msg, objs);
    }
}
