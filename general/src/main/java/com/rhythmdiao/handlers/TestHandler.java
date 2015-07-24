package com.rhythmdiao.handlers;

import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.annotation.RequestParameter;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.Entity;
import com.rhythmdiao.rest.result.BaseRestResult;
import com.rhythmdiao.rest.result.CustomStatusCode;
import com.rhythmdiao.rest.result.json.JsonRestResult;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

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

    @RequestHeader
    private String field1 = "911";

    @RequestParameter
    private String field2 = "bar";

    public BaseRestResult execute(final Request request) {
        BaseRestResult result = new JsonRestResult();
        result.setStatusCode(CustomStatusCode.SUCCESS.getStatusCode());
        result.setMsg("for test");
        HashMap<String, Object> hashMap = newHashMapWithExpectedSize(1);
        Entity entity = new Entity.EntityBuilder().withFoo(Integer.parseInt(field1)).withBar(field2).build();
        hashMap.put("entity", entity);
        result.setResult(hashMap);
        return result;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
