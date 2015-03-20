package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.http.rest.response.json.JsonRestResult;

import java.util.Enumeration;

@Aspect
public final class HandlerLogAspect {
    private static final String EXECUTION = "execution(* handlers.*.execute(..))";
    private static final Logger logger = LoggerFactory.getLogger(HandlerLogAspect.class);

    @Around(EXECUTION)
    public JsonRestResult process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Handler:{}", proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName());
        for (Object o : proceedingJoinPoint.getArgs()) {
            if (o instanceof Request) {
                Request request = (Request) o;
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
            }
        }
        JsonRestResult result = (JsonRestResult) proceedingJoinPoint.proceed();
        logger.info("response:{}", result.convertToResponse());
        return result;
    }
}
