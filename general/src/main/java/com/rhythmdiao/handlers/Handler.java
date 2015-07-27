package com.rhythmdiao.handlers;

import org.eclipse.jetty.server.Request;
import com.rhythmdiao.rest.result.BaseRestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
    BaseRestResult execute(Request request);

    String convertResult(HttpServletRequest request, HttpServletResponse response, BaseRestResult result);
}
