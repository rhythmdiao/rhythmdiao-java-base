package com.rhythmdiao.handler;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.rhythmdiao.constant.LoggerName;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.injection.FieldInjection;
import com.rhythmdiao.result.Parser;
import com.rhythmdiao.util.LogUtil;
import com.rhythmdiao.util.time.TimeCounter;
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
        TimeCounter interval = new TimeCounter().start();
        if (HandlerPath.INSTANCE.isIgnored(target)) return;
        request.setCharacterEncoding(Charsets.UTF_8.name());
        String method = baseRequest.getMethod();
        Register registeredHandler = HandlerPath.INSTANCE.getRegisteredHandler(method, target);
        if (registeredHandler == null) {
            LogUtil.debug(LOG, "Unknown target, and the target is [{}]", target);
            response.setStatus(HttpStatus.NOT_FOUND_404);
        } else if (registeredHandler.getStatus() == Register.Switch.OFF) {
            LogUtil.debug(LOG, "Handler {} is off, method: [{}], and target: [{}]", registeredHandler.getHandlerClass().getCanonicalName(), method, target);
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE_503);
        } else {
            BaseHandler baseHandler;
            try {
                baseHandler = (BaseHandler) Class.forName(registeredHandler.getHandlerClass().getName()).newInstance();
            } catch (Throwable t) {
                throw Throwables.propagate(t);
            }
            if (baseHandler != null) {
                baseHandler.setRequest(request);
                baseHandler.setResponse(response);
                HandlerMetaData metaData = registeredHandler.getMetaData();
                Map<Field, Class<? extends Annotation>> annotatedFields = metaData.getAnnotatedFields();
                Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(annotatedFields.size());
                FieldInjection.INSTANCE.injectField(annotatedFields, request, fieldMap);
                BeanMap beanMap = BeanMap.create(baseHandler);
                beanMap.putAll(fieldMap);
                Parser parser = baseHandler.execute();
                response.setCharacterEncoding(Charsets.UTF_8.name());
                response.setContentType(parser.getContentType());
                response.getWriter().write(parser.toString());
                LOG.debug("The execution of {} took {}ms", baseHandler.getClass().getSimpleName(),
                        registeredHandler.getMonitor().record(interval.end()));
                LOG.debug("avg:{}", registeredHandler.getMonitor().avg());
                LOG.debug("usage:{}", registeredHandler.countUsage());
            }
        }
        baseRequest.setHandled(true);
    }
}
