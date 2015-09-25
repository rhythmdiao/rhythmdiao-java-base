package com.rhythmdiao.handler;

import com.google.common.base.Strings;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.result.AbstractResult;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseHandler implements Handler {
    private static final Logger LOG = LoggerFactory.getLogger("requestParam");
    private Request request;
    private HandlerMetaData handlerMetaData;

    public AbstractResult execute(Request request) {
        return execute();
    }

    private void describeHeader(String... keys) {
        for (String key : keys) {
            String value = request.getHeader(key);
            if (!Strings.isNullOrEmpty(value)) {
                LOG.debug("{}={}", key, value);
            }
        }
    }

    private void describeParameter(String... keys) {
        for (String key : keys) {
            String value = request.getParameter(key);
            if (!Strings.isNullOrEmpty(value)) {
                LOG.debug("{}={}", key, value);
            }
        }
    }

    public void describeRequest(String... keys) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("target={}", handlerMetaData.getTarget());
            describeHeader(keys);
            describeParameter(keys);
        }
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public HandlerMetaData getHandlerMetaData() {
        return handlerMetaData;
    }

    public void setHandlerMetaData(HandlerMetaData handlerMetaData) {
        this.handlerMetaData = handlerMetaData;
    }
}
