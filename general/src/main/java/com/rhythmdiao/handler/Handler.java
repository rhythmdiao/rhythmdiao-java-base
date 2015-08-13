package com.rhythmdiao.handler;

import org.eclipse.jetty.server.Request;
import com.rhythmdiao.result.AbstractResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
    AbstractResult execute(Request request);

    String convertResult(HttpServletRequest request, HttpServletResponse response, AbstractResult result);
}
