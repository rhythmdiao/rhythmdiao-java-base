package com.rhythmdiao.aspect;

import com.rhythmdiao.result.AbstractResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;

@Deprecated
@Aspect
public final class HandlerLogAspect {
    private static final String EXECUTION = "execution(* com.rhythmdiao.handler.BaseHandler.execute(..))";
    private static final Logger LOG = LoggerFactory.getLogger(HandlerLogAspect.class);

    @Pointcut(value = EXECUTION + "&&args(request)", argNames = "request")
    public void pointcut(Request request) {
    }

    @Before(value = "pointcut(request)", argNames = "jointPoint, request")
    public void before(JoinPoint jointPoint, Request request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Handler:{}", jointPoint.getSignature().getDeclaringType().getSimpleName());
            LOG.debug("request headers:");
            Enumeration headerKeys = request.getHeaderNames();
            while (headerKeys.hasMoreElements()) {
                Object key = headerKeys.nextElement();
                LOG.debug("{}:{}", key, request.getHeader((String) key));
            }
            LOG.debug("request parameters:");
            for (Object key : request.getParameterMap().keySet()) {
                LOG.debug("{}:{}", key, request.getParameter((String) key));
            }
        }
    }

    @AfterReturning(value = EXECUTION, argNames = "result", returning = "result")
    public void afterReturning(AbstractResult result) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("response:\n{}", result.to());
        }
    }
}
