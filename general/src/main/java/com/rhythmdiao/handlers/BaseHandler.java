package com.rhythmdiao.handlers;

import com.rhythmdiao.rest.result.BaseRestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseHandler implements Handler {
    private HandlerMetaData handlerMetaData;

    public String convertResult(HttpServletRequest request, HttpServletResponse response, BaseRestResult result) {
        return result.convertResult();
    }

    public HandlerMetaData getHandlerMetaData() {
        return handlerMetaData;
    }

    public void setHandlerMetaData(HandlerMetaData handlerMetaData) {
        this.handlerMetaData = handlerMetaData;
    }
}
