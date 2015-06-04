package com.rhythmdiao.rest;

import com.rhythmdiao.annotation.RestfulHandler;
import com.google.common.base.Charsets;
import com.rhythmdiao.handlers.Handler;
import org.apache.http.entity.ContentType;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rhythmdiao.rest.result.BaseRestResult;
import com.rhythmdiao.rest.result.json.JsonRestResult;
import com.rhythmdiao.rest.result.xml.XMLRestResult;
import com.rhythmdiao.utils.config.ApplicationContextWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

public final class Dispatcher extends AbstractHandler {
    private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);

    public void init() throws IOException {
        Reflections reflections = ApplicationContextWrapper.getBean("reflections", Reflections.class);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RestfulHandler.class);
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

    private void dispatcher(final Class clazz, final RestfulHandler annotation)
            throws ClassNotFoundException {
        final String method = annotation.method();
        final String uri = annotation.uri();
        final Object handler = ApplicationContextWrapper.getBeanByClass(clazz);

        if (!(handler instanceof Handler)) {
            throw new RuntimeException(clazz.toString()
                    + "is not an instance of " + Handler.class);
        }

        LOG.info(String.format("Dispatching %s %s on handler: %s", method, uri, clazz.getName()));
        RequestPathStorage.getInstance().setPathMap(method, uri, handler);
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding(Charsets.UTF_8.name());
        final String method = baseRequest.getMethod();
        final Object handler = RequestPathStorage.getInstance().getPathMap().row(method).get(target);
        if (handler == null) {
            LOG.info("Unknown uri, and the uri is [{}]", target);
        }

        if (handler instanceof Handler) {
            BaseRestResult result = ((Handler) handler).execute(baseRequest);
            response.setCharacterEncoding(Charsets.UTF_8.name());
            if (result instanceof JsonRestResult) {
                response.setContentType(ContentType.TEXT_HTML.getMimeType());
            } else if (result instanceof XMLRestResult) {
                response.setContentType(ContentType.TEXT_XML.getMimeType());
            }
            response.getWriter().write(((Handler) handler).convertToResponse(request, response, result));
            baseRequest.setHandled(true);
        }
    }
}
