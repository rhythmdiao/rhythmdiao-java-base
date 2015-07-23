package com.rhythmdiao.handlers;

import com.rhythmdiao.rest.result.BaseRestResult;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseHandler implements Handler {
    private HandlerInfo handlerInfo;

    public BaseRestResult execute(Request request) {
        return null;
    }

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
