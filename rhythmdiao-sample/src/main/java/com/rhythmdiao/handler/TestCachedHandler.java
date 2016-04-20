package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.annotation.RequestParameter;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.cache.Cache;
import com.rhythmdiao.result.GsonParser;
import com.rhythmdiao.result.Parser;
import com.rhythmdiao.result.Result;
import com.rhythmdiao.result.StatusCode;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

public
@Controller
@RestfulHandler(target = "/cached", description = "测试接口级别缓存接口", cache = 300)
class TestCachedHandler extends CachedHandler{
    @RequestHeader
    private String field1;

    @RequestParameter
    private String field2;

    @Override
    public Parser execute() {
        Result result = new Result();
        result.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        Map<String, String> map = new HashMap<String, String>();
        map.put("field1", field1);
        map.put("field2", field2);
        result.setData(map);
        return new GsonParser(result);
    }

    @Override
    public String getKey() {
        return "cached_" + field1 + "_" + field2;
    }
}
