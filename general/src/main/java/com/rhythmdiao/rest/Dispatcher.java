package com.rhythmdiao.rest;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.handlers.BaseHandler;
import com.rhythmdiao.handlers.Handler;
import com.rhythmdiao.handlers.HandlerMetaData;
import com.rhythmdiao.injection.AbstractInjector;
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
        Reflections reflections = new Reflections("com.rhythmdiao.handlers");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RestfulHandler.class);
        for (Class cls : classes) {
            Annotation[] annotations = cls.getAnnotations();
            for (Annotation annotation : annotations) {
                if (RestfulHandler.class.isInstance(annotation)) {
                    try {
                        dispatcher(cls, (RestfulHandler) annotation);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void addHandlerMetaData(Field[] fields, BaseHandler handler) throws ClassNotFoundException {
        HandlerMetaData handlerMetaData = new HandlerMetaData(fields.length);
        for (Field field : fields) {
            for (Class<? extends AbstractInjector> cls : FieldInjection.INSTANCE.getInjectorList()) {
                try {
                    final Class<? extends Annotation> annotation = cls.newInstance().getAnnotation();
                    if (field.isAnnotationPresent(annotation)) {
                        handlerMetaData.putAnnotatedFields(field, annotation);
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        handler.setHandlerMetaData(handlerMetaData);
    }

    private void dispatcher(final Class cls, final RestfulHandler annotation)
            throws ClassNotFoundException {
        final String method = annotation.method();
        final String uri = annotation.uri();
        final Object handler = ApplicationContextWrapper.getBean(cls);

        if (!(Handler.class.isInstance(handler))) {
            throw new RuntimeException(cls.toString()
                    + "is not an instance of " + Handler.class);
        }
        LOG.info(String.format("Dispatching %s %s on handler: %s", method, uri, cls.getName()));
        addHandlerMetaData(cls.getDeclaredFields(), (BaseHandler) handler);
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
            Map<Field, Class<? extends Annotation>> annotatedFields = baseHandler.getHandlerMetaData().getAnnotatedFields();
            Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(annotatedFields.size());
            try {
                FieldInjection.INSTANCE.injectField(annotatedFields, request, fieldMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            beanMap.putAll(fieldMap);
            BaseRestResult result = baseHandler.execute(baseRequest);
            response.setCharacterEncoding(Charsets.UTF_8.name());
            if (JsonRestResult.class.isInstance(result)) {
                response.setContentType(ContentType.TEXT_HTML.getMimeType());
            } else if (XMLRestResult.class.isInstance(result)) {
                response.setContentType(ContentType.TEXT_XML.getMimeType());
            }
            response.getWriter().write(((Handler) handler).convertResult(request, response, result));
            baseRequest.setHandled(true);
        }
    }
}
