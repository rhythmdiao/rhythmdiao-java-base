package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.CookieParameter;
import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.annotation.RequestParameter;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.Entity;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.result.json.JsonResult;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import java.util.Date;
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
@RestfulHandler(target = "/test", description = "test handler")
class TestHandler extends BaseHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TestHandler.class);

    @RequestHeader
    private int field1;

    @RequestParameter
    private String field2;

    @CookieParameter
    private String field3 = "cookie";

    @RequestParameter
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    public AbstractResult execute(Request request) {
        AbstractResult result = new JsonResult();
        HashMap<String, Object> hashMap = newHashMapWithExpectedSize(1);
        Entity entity = new Entity.EntityBuilder().withFoo(field1).withBar(field2).build();
        hashMap.put("items", entity);
        result.setData(hashMap);
        return result;
    }

    public void setField1(int field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
