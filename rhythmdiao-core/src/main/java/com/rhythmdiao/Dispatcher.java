package com.rhythmdiao;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.handler.BaseHandler;
import com.rhythmdiao.injection.FieldInjection;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.result.json.JsonResult;
import com.rhythmdiao.result.xml.XMLResult;
import com.rhythmdiao.utils.AopUtil;
import com.rhythmdiao.utils.config.ApplicationContextWrapper;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.skife.config.cglib.beans.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
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

    public void init() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RestfulHandler.class));
        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com.rhythmdiao.handler");
        for (BeanDefinition beanDefinition : beanDefinitions) {
            Class cls;
            try {
                cls = Class.forName(beanDefinition.getBeanClassName());
                dispatcher(cls);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatcher(Class cls) throws Exception {
        RestfulHandler annotation = (RestfulHandler) cls.getAnnotation(RestfulHandler.class);
        String method = annotation.method();
        String uri = annotation.target();
        BaseHandler handler = (BaseHandler) ApplicationContextWrapper.getBean(cls);
        RequestPath.INSTANCE.setPathMap(method, uri, handler);
        BaseHandler targetHandler = AopUtils.isAopProxy(handler) ? AopUtil.getCglibProxyTargetObject(handler) : handler;
        LOG.info(String.format("Dispatching %s %s on handler: %s", method, uri, cls.getName()));
        addHandlerMetaData(targetHandler, cls.getDeclaredFields());
    }

    private void addHandlerMetaData(BaseHandler handler, Field[] fields) {
        HandlerMetaData handlerMetaData = new HandlerMetaData(handler, fields.length);
        handlerMetaData.putFields(fields);
        handler.setHandlerMetaData(handlerMetaData);
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if ("/favicon.ico".equals(target)) return;
        request.setCharacterEncoding(Charsets.UTF_8.name());
        String method = baseRequest.getMethod();
        BaseHandler handler = (BaseHandler) RequestPath.INSTANCE.getPathMap().row(method).get(target);
        if (handler == null) {
            LOG.info("Unknown target, and the target is [{}]", target);
        } else {
            try {
                BaseHandler baseHandler = (BaseHandler) Class.forName(handler.getClass().getName()).newInstance();
                baseHandler.setRequest(request);
                baseHandler.setResponse(response);
                baseHandler.setHandlerMetaData(handler.getHandlerMetaData());
                Map<Field, Class<? extends Annotation>> annotatedFields = handler.getHandlerMetaData().getAnnotatedFields();
                Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(annotatedFields.size());
                FieldInjection.INSTANCE.injectField(annotatedFields, request, fieldMap);
                BeanMap beanMap = BeanMap.create(baseHandler);
                beanMap.putAll(fieldMap);
                AbstractResult result = baseHandler.execute();
                response.setCharacterEncoding(Charsets.UTF_8.name());
                if (JsonResult.class.isInstance(result)) {
                    response.setContentType("text/html");
                } else if (XMLResult.class.isInstance(result)) {
                    response.setContentType("text/xml");
                }
                response.getWriter().write(result.specificTo());
                baseRequest.setHandled(true);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
