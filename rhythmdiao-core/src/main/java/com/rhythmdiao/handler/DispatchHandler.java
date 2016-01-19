package com.rhythmdiao.handler;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.rhythmdiao.constant.LoggerName;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.injection.FieldInjection;
import com.rhythmdiao.result.Parser;
import com.rhythmdiao.util.IntervalUtil;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.skife.config.cglib.beans.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public final class DispatchHandler extends AbstractHandler {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerName.DISPATCHER);

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        IntervalUtil interval = new IntervalUtil().start();
        if ("/favicon.ico".equals(target)) return;
        request.setCharacterEncoding(Charsets.UTF_8.name());
        String method = baseRequest.getMethod();
        Register registeredHandler = HandlerPath.INSTANCE.getRegisteredHandler(method, target);
        if (registeredHandler == null) {
            LOG.debug("Unknown target, and the target is [{}]", target);
            response.setStatus(HttpStatus.NOT_FOUND_404);
        } else if (registeredHandler.getStatus() == Register.Switch.OFF) {
            LOG.debug("Handler {} is off, method: [{}], and target: [{}]", registeredHandler.getHandler().getClass().getCanonicalName(), method, target);
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE_503);
        } else {
            BaseHandler baseHandler = null;
            try {
                baseHandler = (BaseHandler) Class.forName(registeredHandler.getHandler().getClass().getName()).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (baseHandler != null) {
                baseHandler.setRequest(request);
                baseHandler.setResponse(response);
                HandlerMetaData metaData = registeredHandler.getHandler().getHandlerMetaData();
                baseHandler.setHandlerMetaData(metaData);
                Map<Field, Class<? extends Annotation>> annotatedFields = metaData.getAnnotatedFields();
                Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(annotatedFields.size());
                FieldInjection.INSTANCE.injectField(annotatedFields, request, fieldMap);
                BeanMap beanMap = BeanMap.create(baseHandler);
                beanMap.putAll(fieldMap);
                Parser parser = baseHandler.execute();
                response.setCharacterEncoding(Charsets.UTF_8.name());
                response.setContentType(parser.getContentType());
                response.getWriter().write(parser.toString());
                LOG.debug("The execution of {} took {}ms", baseHandler.getClass().getSimpleName() ,interval.end());
            }
        }
        baseRequest.setHandled(true);
    }
}
