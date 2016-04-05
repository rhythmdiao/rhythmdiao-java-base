package com.rhythmdiao.handler;

import com.google.common.util.concurrent.AtomicLongMap;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.util.time.TimeMonitor;
import com.rhythmdiao.util.TimeUtil;

public class Register {
    private Class<? extends BaseHandler> handlerClass;

    private HandlerMetaData metaData;

    private Switch status;

    private TimeMonitor monitor;

    private AtomicLongMap<Class<? extends BaseHandler>> count;

    public Register(Class<? extends BaseHandler> handlerClass, HandlerMetaData metaData) {
        this.handlerClass = handlerClass;
        this.metaData = metaData;
        this.monitor = TimeUtil.INSTANCE.getMonitor();
        status = Switch.ON;
        this.count = AtomicLongMap.create();
    }

    public Class<? extends BaseHandler> getHandlerClass() {
        return handlerClass;
    }

    public HandlerMetaData getMetaData() {
        return metaData;
    }

    public void setStatus(Switch status) {
        this.status = status;
    }

    public Switch getStatus() {
        return status;
    }

    public TimeMonitor getMonitor() {
        return monitor;
    }

    public long countUsage() {
        return count.incrementAndGet(handlerClass);
    }

    public void clearUsage() {
        count.clear();
    }

    protected enum Switch {
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
