package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.http.rest.response.json.JsonRestResult;

import java.util.Enumeration;

/**
 * Created by mayuxing on 2015/3/20.
 */
@Aspect
public final class HandlerLogAspect {
    private static final String EXECUTION = "execution(* handlers.*.execute(..))";
    private static final Logger logger = LoggerFactory.getLogger(HandlerLogAspect.class);

    @SuppressWarnings("unchecked")
    @AfterReturning(value = EXECUTION, returning = "returnObj")
    public void log(JoinPoint joinPoint, JsonRestResult returnObj) throws NoSuchMethodException {
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof Request) {
                Request request = (Request) o;
                logger.info("--->request headers<---");
                Enumeration headerKeys = request.getHeaderNames();
                while (headerKeys.hasMoreElements()) {
                    Object key = headerKeys.nextElement();
                    logger.debug("{}:{}", key, request.getHeader((String) key));
                }
                logger.info("--->request parameters<---");
                for (Object key : request.getParameterMap().keySet()) {
                    logger.info("{}:{}", key, request.getParameter((String) key));
                }
            }
        }
        logger.info("response:{}", returnObj.convertToResponse());
    }
}
