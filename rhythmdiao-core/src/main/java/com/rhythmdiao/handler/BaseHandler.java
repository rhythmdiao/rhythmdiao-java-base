package com.rhythmdiao.handler;

import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.result.AbstractResult;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class BaseHandler implements Handler {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HandlerMetaData handlerMetaData;
    private Switch status = Switch.ON;

    public AbstractResult execute(Request request) {
        return execute();
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

    public Switch getStatus() {
        return status;
    }

    public void setStatus(Switch status) {
        this.status = status;
    }

    public enum Switch {
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
