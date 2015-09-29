package com.rhythmdiao.handler;

import com.google.common.base.Strings;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.result.AbstractResult;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class BaseHandler implements Handler {
    private static final Logger LOG = LoggerFactory.getLogger("requestParam");
    private HttpServletRequest request;
    private HttpServletResponse response;
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

    public void addResponseHeader(Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            response.addHeader(header.getKey(), header.getValue());
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HandlerMetaData getHandlerMetaData() {
        return handlerMetaData;
    }

    public void setHandlerMetaData(HandlerMetaData handlerMetaData) {
        this.handlerMetaData = handlerMetaData;
    }
}
