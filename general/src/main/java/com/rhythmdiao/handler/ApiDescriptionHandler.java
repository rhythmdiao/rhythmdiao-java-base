package com.rhythmdiao.handler;

import com.rhythmdiao.RequestPath;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.HandlerMetaDataList;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.result.StatusCode;
import com.rhythmdiao.result.json.JsonResult;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

public
@Controller
@RestfulHandler(target = "/", description = "all apis description handler")
class ApiDescriptionHandler extends BaseHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ApiDescriptionHandler.class);

    @Override
    public AbstractResult execute(Request request) {
        AbstractResult result = new JsonResult(true);
        for (Object o : RequestPath.INSTANCE.getPathMap().values()) {
            BaseHandler handler = (BaseHandler) o;
            HandlerMetaDataList.INSTANCE.add(handler.getHandlerMetaData());
        }

        HashMap<String, Object> data = new HashMap<String, Object>(1);
        data.put("API", HandlerMetaDataList.INSTANCE.get());
        result.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        result.setData(data);
        return result;
    }
}
