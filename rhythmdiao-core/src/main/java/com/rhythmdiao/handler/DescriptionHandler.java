package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.HandlerMetaDataList;
import com.rhythmdiao.result.GsonParser;
import com.rhythmdiao.result.Parser;
import com.rhythmdiao.result.Result;
import com.rhythmdiao.result.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

public
@Controller
@RestfulHandler(target = "/description", description = "接口元数据")
class DescriptionHandler extends BaseHandler {
    private static final Logger LOG = LoggerFactory.getLogger(DescriptionHandler.class);

    @Override
    public Parser execute() {
        Result result = new Result();
        for (Register registeredHandler : HandlerPath.INSTANCE.getPath().values()) {
            HandlerMetaDataList.INSTANCE.add(registeredHandler.getMetaData());
        }

        HashMap<String, Object> data = new HashMap<String, Object>(1);
        data.put("API", HandlerMetaDataList.INSTANCE.get());
        result.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        result.setData(data);
        return new GsonParser(result);
    }
}
