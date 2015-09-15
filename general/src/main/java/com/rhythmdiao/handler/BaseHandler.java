package com.rhythmdiao.handler;

import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.result.AbstractResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseHandler implements Handler {
    private HandlerMetaData handlerMetaData;

    public String convertResult(HttpServletRequest request, HttpServletResponse response, AbstractResult result) {
        return result.convertResult();
    }

    public HandlerMetaData getHandlerMetaData() {
        return handlerMetaData;
    }

    public void setHandlerMetaData(HandlerMetaData handlerMetaData) {
        this.handlerMetaData = handlerMetaData;
    }
}
