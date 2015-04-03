package handlers;

import annotation.ClassInfo;
import annotation.MethodInfo;
import annotation.RestfulHandler;
import api.http.Handler;
import com.google.common.collect.Maps;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import rest.result.BaseRestResult;
import rest.result.CustomStatusCode;
import rest.result.json.JsonRestResult;

import java.util.HashMap;

@ClassInfo(author = "", description = "test handler", date = "2015.4.3")
public
@Controller
@RestfulHandler(uri = "/test", method = HttpMethods.GET)
class TestHandler implements Handler {
    private static final Logger LOG = LoggerFactory.getLogger(TestHandler.class);

    @MethodInfo(author = "", description = "execute method", date = "2015.4.3")
    public
    @Override
    BaseRestResult execute(final Request request) {
        JsonRestResult result = new JsonRestResult();
        result.setStatusCode(CustomStatusCode.SUCCESS.getStatusCode());
        result.setMsg("for test");
        HashMap<String, String> hashMap = Maps.newHashMapWithExpectedSize(4);
        hashMap.put("text", request.getHeader("text"));
        hashMap.put("num", request.getHeader("num"));
        hashMap.put("text2", request.getParameter("text2"));
        hashMap.put("num2", request.getParameter("num2"));
        result.setResult(hashMap);
        return result;
    }
}
