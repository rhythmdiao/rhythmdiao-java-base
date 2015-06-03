package handlers;

import annotation.RestfulHandler;
import entity.Entity;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import rest.result.BaseRestResult;
import rest.result.CustomStatusCode;
import rest.result.json.JsonRestResult;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMapWithExpectedSize;
/*
* Design the HTTP codes and responses you want
* Common patterns:
* Errors
* Responses & data stubs
* URLs
* Versions
* Data formats
* */
public
@Controller
@RestfulHandler(uri = "/test")
class TestHandler extends BaseHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TestHandler.class);

    public BaseRestResult execute(final Request request) {
        BaseRestResult result = new JsonRestResult();
        result.setStatusCode(CustomStatusCode.SUCCESS.getStatusCode());
        result.setMsg("for test");
        HashMap<String, Object> hashMap = newHashMapWithExpectedSize(1);
        Entity entity = new Entity.EntityBuilder().withFoo(1).withBar("name").build();
        hashMap.put("entity", entity);
        LOG.info("{}", entity.toString());
        result.setResult(hashMap);
        return result;
    }
}
