package com.rhythmdiao.aspect;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.result.json.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;

@Aspect
public final class HandlerLogAspect {
    private static final String EXECUTION = "execution(* com.rhythmdiao.handlers.*.execute(..))";
    private static final Logger LOG = LoggerFactory.getLogger(HandlerLogAspect.class);

    @Around(EXECUTION + "&&args(request)")
    public AbstractResult processExecution(ProceedingJoinPoint proceedingJoinPoint, final Request request) throws Throwable {
        Class clazz = proceedingJoinPoint.getSignature().getDeclaringType();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Handler:{}", clazz.getSimpleName());
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

        RestfulHandler restfulHandler = (RestfulHandler) clazz.getAnnotation(RestfulHandler.class);
        final String identification = restfulHandler.identification();
        final String requestIdentification = request.getParameter("identification");
        AbstractResult result;
        if (Strings.isNullOrEmpty(identification)) {
            result = (AbstractResult) proceedingJoinPoint.proceed();
        } else if (!Strings.isNullOrEmpty(identification) && !Strings.isNullOrEmpty(requestIdentification) && identification.equals(requestIdentification)) {
            result = (AbstractResult) proceedingJoinPoint.proceed();
        } else {
            result = new JsonResult();
        }
        LOG.info("response:\n{}", result.convertResult());
        return result;
    }
}
