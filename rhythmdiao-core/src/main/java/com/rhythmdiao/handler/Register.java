package com.rhythmdiao.handler;

import com.google.common.util.concurrent.AtomicLongMap;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.util.time.TimeMonitor;
import com.rhythmdiao.util.TimeUtil;

class Register {
    private Class<? extends BaseHandler> handlerClass;

    private HandlerMetaData metaData;

    private Switch status;

    private TimeMonitor monitor;

    private AtomicLongMap<Class<? extends BaseHandler>> count;

    Register(Class<? extends BaseHandler> handlerClass, HandlerMetaData metaData) {
        this.handlerClass = handlerClass;
        this.metaData = metaData;
        this.monitor = TimeUtil.INSTANCE.getMonitor();
        status = Switch.ON;
        this.count = AtomicLongMap.create();
    }

    Class<? extends BaseHandler> getHandlerClass() {
        return handlerClass;
    }

    HandlerMetaData getMetaData() {
        return metaData;
    }

    void setStatus(Switch status) {
        this.status = status;
    }

    Switch getStatus() {
        return status;
    }

    TimeMonitor getMonitor() {
        return monitor;
    }

    long countUsage() {
        return count.incrementAndGet(handlerClass);
    }

    void clearUsage() {
        count.clear();
    }

    enum Switch {
        ON(true), OFF(false);

        public boolean value() {
            return status;
        }

        Switch(boolean status) {
            this.status = status;
        }

        private boolean status;
    }
}
