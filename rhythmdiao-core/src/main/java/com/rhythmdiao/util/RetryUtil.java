package com.rhythmdiao.util;

import com.rhythmdiao.operation.Manipulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RetryUtil extends Manipulator {
    private static final Logger LOG = LoggerFactory.getLogger(RetryUtil.class);

    private int retry = 3;

    public RetryUtil() {
    }

    public RetryUtil(int retry) {
        this.retry = retry;
    }

    public abstract boolean execute();

    public boolean isDone() {
        while (retry-- > 0) {
            try {
                if (execute()) return true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return false;
    }

}
