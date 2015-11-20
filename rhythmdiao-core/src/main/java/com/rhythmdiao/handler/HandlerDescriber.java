package com.rhythmdiao.handler;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerDescriber {
    private static final Logger LOG = LoggerFactory.getLogger("handlerDescriber");
    private BaseHandler handler;

    public HandlerDescriber(BaseHandler handler) {
        this.handler = handler;
    }

    private void describeHeader(String... keys) {
        for (String key : keys) {
            String value = handler.getRequest().getHeader(key);
            if (!Strings.isNullOrEmpty(value)) {
                LOG.debug("{}={}", key, value);
            }
        }
    }

    private void describeParameter(String... keys) {
        for (String key : keys) {
            String value = handler.getRequest().getParameter(key);
            if (!Strings.isNullOrEmpty(value)) {
                LOG.debug("{}={}", key, value);
            }
        }
    }

    public void describeRequest(String... keys) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("target={}", handler.getHandlerMetaData().getTarget());
            describeHeader(keys);
            describeParameter(keys);
        }
    }
}
