package utils.http.rest;

import annotation.RestfulHandler;
import api.http.Handler;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.http.PathMap;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.config.ApplicationContextWrapper;
import utils.http.rest.response.BaseRestResult;
import utils.tool.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

public class Dispatcher extends AbstractHandler {
    private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);
    private static final PathMap getPathMapper = new PathMap(255);
    private static final PathMap postPathMapper = new PathMap(255);

    public void init() throws IOException {
        Set<Class<?>> classes = CommonUtil.AnnotatedWith(RestfulHandler.class);
        for (Class clazz : classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof RestfulHandler) {
                    try {
                        dispatcher(clazz, (RestfulHandler) annotation);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void dispatcher(Class clazz, RestfulHandler annotation)
            throws ClassNotFoundException {
        String method = annotation.method();
        String uri = annotation.uri();

        Object handler = ApplicationContextWrapper.getBeanByClass(clazz);
        if (!(handler instanceof Handler)) {
            throw new RuntimeException(clazz.toString()
                    + "is not an instance of " + Handler.class);
        }

        logger.info(String.format("Dispatching %s %s on handler: %s", method, uri, clazz.getName()));

        if (method.equals(HttpMethods.GET)) {
            getPathMapper.put(uri, handler);
        } else if (method.equals(HttpMethods.POST)) {
            postPathMapper.put(uri, handler);
        }
    }

    @Override
    public void handle(String uri, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        final String method = baseRequest.getMethod();
        final Object handler;

        if (method.equals(HttpMethods.GET)) {
            try {
                handler = getPathMapper.getMatch(uri)
                        .getValue();
            } catch (NullPointerException e) {
                logger.error("Unknown uri, and the uri is [{}]", uri);
                return;
            }

        } else if (method.equals(HttpMethods.POST)) {
            try {
                handler = postPathMapper.getMatch(uri)
                        .getValue();
            } catch (NullPointerException e) {
                logger.error("Unknown uri, and uri is [{}]", uri);
                return;
            }
        } else {
            return;
        }
        if (handler instanceof Handler) {
            BaseRestResult result = ((Handler) handler).execute(baseRequest);
            response.getWriter().write(result.convertToResponse());
            baseRequest.setHandled(true);
        }
    }
}
