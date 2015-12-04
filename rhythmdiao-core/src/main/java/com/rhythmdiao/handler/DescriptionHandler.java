package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.HandlerMetaDataList;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.result.StatusCode;
import com.rhythmdiao.result.json.JsonResult;
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
    public AbstractResult execute() {
        AbstractResult result = new JsonResult(true);
        for (Register registeredHandler : HandlerPath.INSTANCE.getPath().values()) {
            BaseHandler handler = registeredHandler.getHandler();
            HandlerMetaDataList.INSTANCE.add(handler.getHandlerMetaData());
        }

        HashMap<String, Object> data = new HashMap<String, Object>(1);
        data.put("API", HandlerMetaDataList.INSTANCE.get());
        result.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        result.setData(data);
        return result;
    }
}
