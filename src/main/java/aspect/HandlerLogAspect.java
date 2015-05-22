package aspect;

import annotation.RestfulHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.result.BaseRestResult;
import rest.result.json.JsonRestResult;

import java.util.Enumeration;

import static com.google.common.base.Strings.isNullOrEmpty;

@Aspect
public final class HandlerLogAspect {
    private static final String EXECUTION = "execution(* handlers.*.execute(..))";
    private static final Logger LOG = LoggerFactory.getLogger(HandlerLogAspect.class);

    @Around(EXECUTION + "&&args(request)")
    public BaseRestResult processExecution(ProceedingJoinPoint proceedingJoinPoint, final Request request) throws Throwable {
        LOG.info("Handler:{}", proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName());
        LOG.info("request headers:");
        Enumeration headerKeys = request.getHeaderNames();
        while (headerKeys.hasMoreElements()) {
            Object key = headerKeys.nextElement();
            LOG.debug("{}:{}", key, request.getHeader((String) key));
        }
        LOG.info("request parameters:");
        for (Object key : request.getParameterMap().keySet()) {
            LOG.info("{}:{}", key, request.getParameter((String) key));
        }
        RestfulHandler restfulHandler = (RestfulHandler) proceedingJoinPoint.getSignature().getDeclaringType().getAnnotation(RestfulHandler.class);
        final String identification = restfulHandler.identification();
        final String requestIdentification = request.getParameter("identification");
        BaseRestResult result;
        if (isNullOrEmpty(identification)) {
            result = (BaseRestResult) proceedingJoinPoint.proceed();
        } else if (!isNullOrEmpty(identification) && !isNullOrEmpty(requestIdentification) && identification.equals(requestIdentification)) {
            result = (BaseRestResult) proceedingJoinPoint.proceed();
        } else {
            result = new JsonRestResult();
        }
        LOG.info("response:\n{}", result.convertToResponse());
        return result;
    }
}
