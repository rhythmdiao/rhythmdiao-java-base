package handlers;

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

@Controller
@RestfulHandler(uri = "/test", method = HttpMethods.GET)
public class TestHandler implements Handler {
    private static final Logger LOG = LoggerFactory.getLogger(TestHandler.class);

    @Override
    public BaseRestResult execute(final Request request) {
        JsonRestResult result = new JsonRestResult();
        result.setStatusCode(CustomStatusCode.SUCCESS.getStatusCode());
        result.setMsg("for test");
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("text", request.getHeader("text"));
        hashMap.put("num", request.getHeader("num"));
        hashMap.put("text2",request.getParameter("text2"));
        hashMap.put("num2", request.getParameter("num2"));
        result.setResult(hashMap);
        return result;
    }
}
