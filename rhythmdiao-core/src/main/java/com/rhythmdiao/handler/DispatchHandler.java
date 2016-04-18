package com.rhythmdiao.handler;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.rhythmdiao.cache.HandlerCache;
import com.rhythmdiao.cache.HandlerCacheFactory;
import com.rhythmdiao.cache.HandlerCacheManager;
import com.rhythmdiao.cache.jedis.JedisManagerHandler;
import com.rhythmdiao.constant.LoggerName;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.injection.FieldInjection;
import com.rhythmdiao.result.GsonParser;
import com.rhythmdiao.result.Parser;
import com.rhythmdiao.result.Result;
import com.rhythmdiao.util.LogUtil;
import com.rhythmdiao.util.time.TimeCounter;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
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

    private HandlerCacheFactory handlerCacheFactory;

    private HandlerCacheManager firstCache;

    //TODO
    private HandlerCacheManager secondCache;

    public void setFirstCache(HandlerCacheManager firstCache) {
        this.firstCache = firstCache;
    }

    public void init() {
        handlerCacheFactory = HandlerCacheFactory.getInstance();
        handlerCacheFactory.setFirstCache(firstCache);
    }

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
            BaseHandler handler;
            try {
                handler = (BaseHandler) Class.forName(registeredHandler.getHandlerClass().getName()).newInstance();
            } catch (Throwable t) {
                throw Throwables.propagate(t);
            }
            if (handler != null) {
                HandlerMetaData metaData = registeredHandler.getMetaData();
                injectHandler(handler, metaData, request, response);
                Parser parser;
                if (handler instanceof CachedHandler) {
                    CachedHandler cachedHandler = (CachedHandler) handler;
                    LOG.debug("cache key is {}", cachedHandler.getKey());
                    Result cachedResult = cachedResult(cachedHandler);
                    if (cachedResult != null) {
                        parser = new GsonParser(cachedResult);
                    } else {
                        parser = handler.execute();
                        handlerCacheFactory.getHandlerCache(cachedHandler.getKey()).set(parser.getResult(), metaData.getCache());
                    }
                } else {
                    parser = handler.execute();
                }
                response.setCharacterEncoding(Charsets.UTF_8.name());
                response.setContentType(parser.getContentType());
                response.getWriter().write(parser.toString());
                LOG.debug("The execution of {} took {}ms", handler.getClass().getSimpleName(),
                        registeredHandler.getMonitor().record(interval.end()));
                LOG.debug("avg:{}", registeredHandler.getMonitor().avg());
                LOG.debug("usage:{}", registeredHandler.countUsage());
            }
            baseRequest.setHandled(true);
        }
    }

    private void injectHandler(BaseHandler handler, HandlerMetaData metaData, HttpServletRequest request, HttpServletResponse response) {
        handler.setRequest(request);
        handler.setResponse(response);
        Map<Field, Class<? extends Annotation>> annotatedFields = metaData.getAnnotatedFields();
        FieldInjection.INSTANCE.injectField(handler, annotatedFields, request);
    }

    private Result cachedResult(CachedHandler handler) {
        if (handler.getKey() == null || handler.getKey().equals("")) {
            return null;
        } else {
            return handlerCacheFactory.getHandlerCache(handler.getKey()).get(Result.class);
        }
    }
}
