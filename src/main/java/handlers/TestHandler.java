package handlers;

import annotation.RestfulHandler;
import api.http.Handler;
import com.google.common.collect.Maps;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import utils.http.rest.response.BaseRestResult;
import utils.http.rest.response.CustomStatusCode;
import utils.http.rest.response.json.JsonRestResult;

import java.util.HashMap;

@Controller
@RestfulHandler(uri = "/test", method = HttpMethods.GET)
public class TestHandler implements Handler {
    private static Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @Override
    public BaseRestResult execute(final Request request) {
        JsonRestResult result = new JsonRestResult();
        result.setStatusCode(CustomStatusCode.SUCCESS.getStatusCode());
        result.setMsg("for test");
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("text", request.getHeader("text"));
        hashMap.put("num", request.getHeader("num"));
        result.setResult(hashMap);
        return result;
    }
}
