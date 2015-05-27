package handlers;

import org.eclipse.jetty.server.Request;
import rest.result.BaseRestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
    BaseRestResult execute(Request request);

    String convertToResponse(HttpServletRequest request, HttpServletResponse response, BaseRestResult result);
}
