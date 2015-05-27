package rest;

import annotation.RestfulHandler;
import handlers.Handler;
import com.google.common.base.Charsets;
import org.apache.http.entity.ContentType;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.http.PathMap;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.result.BaseRestResult;
import rest.result.json.JsonRestResult;
import rest.result.xml.XMLRestResult;
import utils.config.ApplicationContextWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

public final class Dispatcher extends AbstractHandler {
    private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);
    private static final PathMap getPathMapper = new PathMap();
    private static final PathMap postPathMapper = new PathMap();

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

        if (method.equals(HttpMethods.GET)) {
            getPathMapper.put(uri, handler);
        } else if (method.equals(HttpMethods.POST)) {
            postPathMapper.put(uri, handler);
        }
    }

    public void handle(String uri, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding(Charsets.UTF_8.name());
        final String method = baseRequest.getMethod();
        final Object handler;

        if (method.equals(HttpMethods.GET)) {
            try {
                handler = getPathMapper.getMatch(uri)
                        .getValue();
            } catch (NullPointerException e) {
                LOG.error("Unknown uri, and the uri is [{}]", uri);
                return;
            }

        } else if (method.equals(HttpMethods.POST)) {
            try {
                handler = postPathMapper.getMatch(uri)
                        .getValue();
            } catch (NullPointerException e) {
                LOG.error("Unknown uri, and uri is [{}]", uri);
                return;
            }
        } else {
            return;
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
