package com.rhythmdiao.rest;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.handlers.BaseHandler;
import com.rhythmdiao.handlers.Handler;
import com.rhythmdiao.handlers.HandlerInfo;
import com.rhythmdiao.injection.AnnotationStorage;
import com.rhythmdiao.injection.FieldInjection;
import com.rhythmdiao.rest.result.BaseRestResult;
import com.rhythmdiao.rest.result.json.JsonRestResult;
import com.rhythmdiao.rest.result.xml.XMLRestResult;
import com.rhythmdiao.utils.config.ApplicationContextWrapper;
import org.apache.http.entity.ContentType;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.reflections.Reflections;
import org.skife.config.cglib.beans.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public final class Dispatcher extends AbstractHandler {
    private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);

    public void init() throws IOException {
        Reflections reflections = ApplicationContextWrapper.getBean("reflections", Reflections.class);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RestfulHandler.class);
        for (Class clazz : classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (RestfulHandler.class.isInstance(annotation)) {
                    try {
                        dispatcher(clazz, (RestfulHandler) annotation);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void addHandlerInfo(Class clazz, BaseHandler handler) throws ClassNotFoundException {
        HandlerInfo handlerInfo = new HandlerInfo();
        for (Field field : clazz.getDeclaredFields()) {
            for (Class<? extends Annotation> annotation : AnnotationStorage.getInstance().getCustomAnnotationList())
                if (field.isAnnotationPresent(annotation)) {
                    handlerInfo.setFieldwithAnnotationMap(annotation, handlerInfo.new FieldWithAnnotation(field, field.getAnnotation(annotation)));
                }
        }
        handler.setHandlerInfo(handlerInfo);
    }

    private void dispatcher(final Class clazz, final RestfulHandler annotation)
            throws ClassNotFoundException {
        final String method = annotation.method();
        final String uri = annotation.uri();
        final Object handler = ApplicationContextWrapper.getBeanByClass(clazz);

        if (!(Handler.class.isInstance(handler))) {
            throw new RuntimeException(clazz.toString()
                    + "is not an instance of " + Handler.class);
        }
        addHandlerInfo(clazz, (BaseHandler) handler);
        LOG.info(String.format("Dispatching %s %s on handler: %s", method, uri, clazz.getName()));
        RequestPathStorage.INSTANCE.setPathMap(method, uri, handler);
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding(Charsets.UTF_8.name());
        final String method = baseRequest.getMethod();
        final Object handler = RequestPathStorage.INSTANCE.getPathMap().row(method).get(target);
        if (handler == null) {
            LOG.info("Unknown uri, and the uri is [{}]", target);
        }

        if (Handler.class.isInstance(handler)) {
            BaseHandler baseHandler = (BaseHandler) handler;
            BeanMap beanMap = BeanMap.create(baseHandler);
            Map<String, Object> injectedFieldMap = Maps.newHashMap();
            try {
                injectedFieldMap = FieldInjection.INSTANCE.injectField(baseHandler.getHandlerInfo(), request);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            beanMap.putAll(injectedFieldMap);
            BaseRestResult result = baseHandler.execute(baseRequest);
            response.setCharacterEncoding(Charsets.UTF_8.name());
            if (JsonRestResult.class.isInstance(result)) {
                response.setContentType(ContentType.TEXT_HTML.getMimeType());
            } else if (XMLRestResult.class.isInstance(result)) {
                response.setContentType(ContentType.TEXT_XML.getMimeType());
            }
            response.getWriter().write(((Handler) handler).convertToResponse(request, response, result));
            baseRequest.setHandled(true);
        }
    }
}
