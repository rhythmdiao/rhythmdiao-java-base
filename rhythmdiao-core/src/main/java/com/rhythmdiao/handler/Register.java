package com.rhythmdiao.handler;

import com.rhythmdiao.entity.HandlerMetaData;

public class Register {
    private Class<? extends BaseHandler> handlerClass;

    private HandlerMetaData metaData;

    private Switch status;

    public Register(Class<? extends BaseHandler> handlerClass, HandlerMetaData metaData) {
        this.handlerClass = handlerClass;
        this.metaData = metaData;
        status = Switch.ON;
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
