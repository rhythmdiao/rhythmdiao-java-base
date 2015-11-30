package com.rhythmdiao.handler;

import com.rhythmdiao.RequestPath;
import com.rhythmdiao.annotation.RequestParameter;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.result.StatusCode;
import com.rhythmdiao.result.json.JsonResult;
import org.eclipse.jetty.http.HttpMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

public
@Controller
@RestfulHandler(target = "/switch", description = "switch for handlers", method = HttpMethods.POST)
class SwitchHandler extends BaseHandler {
    private static final Logger LOG = LoggerFactory.getLogger(SwitchHandler.class);

    @RequestParameter(value = "method")
    private String method;

    @RequestParameter(value = "target")
    private String target;

    @Override
    public AbstractResult execute() {
        JsonResult result = new JsonResult();
        BaseHandler handler = (BaseHandler) RequestPath.INSTANCE.getPathMap().row(method).get(target);
        if (handler != null) {
            handler.setStatus(handler.getStatus().value() ? Switch.OFF : Switch.ON);
            result.setStatusMsg("Switch handler (method: [" + method + "] target: [" + target + "]) to " + handler.getStatus().name());
        } else {
            result.setStatusMsg("Unknown handler (method: [" + method + "] target: [" + target + "])");
        }
        result.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        return result;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
