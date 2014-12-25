package util.http.rest;

import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.http.PathMap;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.annotation.rest.RestfulServlet;
import util.config.ApplicationContextWrapper;
import util.http.rest.response.BaseRestResult;
import util.tool.Reflection;

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
        Set<Class<?>> classes = Reflection.AnnotatedWith(RestfulServlet.class);
        for (Class clazz : classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof RestfulServlet) {
                    try {
                        dispatch(clazz, (RestfulServlet) annotation);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void dispatch(Class clazz, RestfulServlet annotation)
            throws ClassNotFoundException {
        String method = annotation.Method();
        String uri = annotation.URI();

        Object handler = ApplicationContextWrapper.getBeanByClass(clazz);
        if (!(handler instanceof Handler)) {
            throw new RuntimeException(clazz.toString()
                    + "never found rest handler");
        }

        logger.info(String.format("Dispatching %s %s on handler: %s", method, uri, clazz.getName()));

        if (method.equals(HttpMethods.GET)) {
            postPathMapper.put(uri, handler);
        } else if (method.equals(HttpMethods.POST)) {
            getPathMapper.put(uri, handler);
        }
    }

    @Override
    public void handle(String uri, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        final String method = baseRequest.getMethod();
        final Object restServlet;

        if (method.equals(HttpMethods.GET)) {
            try {
                restServlet = postPathMapper.getMatch(uri)
                        .getValue();
            } catch (NullPointerException e) {
                logger.error("Unknown uri, and the uri is [{}]", uri);
                return;
            }

        } else if (method.equals(HttpMethods.POST)) {
            try {
                restServlet = getPathMapper.getMatch(uri)
                        .getValue();
            } catch (NullPointerException e) {
                logger.error("Unknown uri, and uri is [{}]", uri);
                return;
            }
        } else {
            return;
        }
        if (restServlet instanceof Handler) {
            BaseRestResult result = ((Handler) restServlet).execute(baseRequest);
            response.getWriter().write(result.convertToResponse());
            baseRequest.setHandled(true);
        }
    }
}
