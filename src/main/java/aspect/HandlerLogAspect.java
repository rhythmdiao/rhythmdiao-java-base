package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.result.BaseRestResult;

import java.util.Enumeration;

@Aspect
public final class HandlerLogAspect {
    private static final String EXECUTION = "execution(* handlers.*.execute(..))";
    private static final Logger logger = LoggerFactory.getLogger(HandlerLogAspect.class);

    @Around(EXECUTION + "&&args(request)")
    public BaseRestResult process(ProceedingJoinPoint proceedingJoinPoint, final Request request) throws Throwable {
        logger.info("Handler:{}", proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName());
        logger.info("request headers:");
        Enumeration headerKeys = request.getHeaderNames();
        while (headerKeys.hasMoreElements()) {
            Object key = headerKeys.nextElement();
            logger.debug("{}:{}", key, request.getHeader((String) key));
        }
        logger.info("request parameters:");
        for (Object key : request.getParameterMap().keySet()) {
            logger.info("{}:{}", key, request.getParameter((String) key));
        }
        BaseRestResult result = (BaseRestResult) proceedingJoinPoint.proceed();
        logger.info("response:{}", result.convertToResponse());
        return result;
    }
}
