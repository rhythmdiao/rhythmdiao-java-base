package com.rhythmdiao;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.handler.BaseHandler;
import com.rhythmdiao.handler.Handler;
import com.rhythmdiao.injection.FieldInjection;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.result.json.JsonResult;
import com.rhythmdiao.result.xml.XMLResult;
import com.rhythmdiao.utils.config.ApplicationContextWrapper;
import org.apache.http.entity.ContentType;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.skife.config.cglib.beans.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public final class Dispatcher extends AbstractHandler {
    private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);

    public void init() throws IOException, ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RestfulHandler.class));
        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com.rhythmdiao.handler");
        for (BeanDefinition beanDefinition : beanDefinitions) {
            Class cls = Class.forName(beanDefinition.getBeanClassName());
            dispatcher(cls);
        }
    }

    private void addHandlerMetaData(Field[] fields, BaseHandler handler, String method, String uri) throws ClassNotFoundException {
        HandlerMetaData handlerMetaData = new HandlerMetaData(handler, method, uri, fields.length);
        handlerMetaData.putAnnotatedFields(fields);
        handler.setHandlerMetaData(handlerMetaData);
    }

    private void dispatcher(final Class cls) throws ClassNotFoundException {
        final RestfulHandler annotation = (RestfulHandler) cls.getAnnotation(RestfulHandler.class);
        final String method = annotation.method();
        final String uri = annotation.uri();
        final Object handler = ApplicationContextWrapper.getBean(cls);

        if (!(Handler.class.isInstance(handler))) {
            throw new RuntimeException(cls.toString()
                    + "is not an instance of " + Handler.class);
        }
        LOG.info(String.format("Dispatching %s %s on handler: %s", method, uri, cls.getName()));
        addHandlerMetaData(cls.getDeclaredFields(), (BaseHandler) handler, method, uri);
        RequestPath.INSTANCE.setPathMap(method, uri, handler);
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding(Charsets.UTF_8.name());
        final String method = baseRequest.getMethod();
        final Object handler = RequestPath.INSTANCE.getPathMap().row(method).get(target);
        if (handler == null && !"/favicon.ico".equals(target)) {
            LOG.info("Unknown uri, and the uri is [{}]", target);
        }

        if (Handler.class.isInstance(handler)) {
            BaseHandler baseHandler = (BaseHandler) handler;
            Map<Field, Class<? extends Annotation>> annotatedFields = baseHandler.getHandlerMetaData().getAnnotatedFields();
            Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(annotatedFields.size());
            try {
                FieldInjection.INSTANCE.injectField(annotatedFields, request, fieldMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            BeanMap beanMap = BeanMap.create(baseHandler);
            beanMap.putAll(fieldMap);
            AbstractResult result = baseHandler.execute(baseRequest);
            response.setCharacterEncoding(Charsets.UTF_8.name());
            if (JsonResult.class.isInstance(result)) {
                response.setContentType(ContentType.TEXT_HTML.getMimeType());
            } else if (XMLResult.class.isInstance(result)) {
                response.setContentType(ContentType.TEXT_XML.getMimeType());
            }
            response.getWriter().write(((Handler) handler).convertResult(request, response, result));
            baseRequest.setHandled(true);
        }
    }
}
