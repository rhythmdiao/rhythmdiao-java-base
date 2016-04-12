package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.*;
import com.rhythmdiao.entity.Entity;
import com.rhythmdiao.result.GsonParser;
import com.rhythmdiao.result.Parser;
import com.rhythmdiao.result.Result;
import com.rhythmdiao.result.StatusCode;
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
@RestfulHandler(target = "/test", description = "测试接口")
class TestHandler extends BaseHandler {
    @Describer(comment = "/test")
    @RequestHeader
    private int field1;

    @RequestParameter
    private String field2;

    @CookieParameter
    private String field3;

    @RequestParameter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Parser execute() {
        Result result = new Result();
        HashMap<String, Object> hashMap = newHashMapWithExpectedSize(1);
        Entity entity = new Entity.EntityBuilder().withFoo(field1).withBar(field2).build();
        hashMap.put("items", entity);
        result.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        result.setData(hashMap);
        return new GsonParser(result);
    }
}
