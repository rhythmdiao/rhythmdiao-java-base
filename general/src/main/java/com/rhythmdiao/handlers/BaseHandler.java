package com.rhythmdiao.handlers;

import com.rhythmdiao.rest.result.BaseRestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseHandler implements Handler {
    private HandlerInfo handlerInfo;

    public String convertToResponse(HttpServletRequest request, HttpServletResponse response, BaseRestResult result) {
        return result.convertToResponse();
    }

    public HandlerInfo getHandlerInfo() {
        return handlerInfo;
    }

    public void setHandlerInfo(HandlerInfo handlerInfo) {
        this.handlerInfo = handlerInfo;
    }
}
