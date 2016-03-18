package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.RequestParameter;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.result.GsonParser;
import com.rhythmdiao.result.Parser;
import com.rhythmdiao.result.Result;
import com.rhythmdiao.result.StatusCode;
import org.eclipse.jetty.http.HttpMethods;
import org.springframework.stereotype.Controller;

public
@Controller
@RestfulHandler(target = "/switch", description = "接口开关", method = HttpMethods.POST)
class SwitchHandler extends BaseHandler {
    @RequestParameter(value = "method")
    private String method;

    @RequestParameter(value = "target")
    private String target;

    @Override
    public Parser execute() {
        Result result = new Result();
        if (target.equalsIgnoreCase("/switch")) {
            result.setStatusMsg("The switch handler can not be turned off!");
        } else {
            Register registeredHandler = HandlerPath.INSTANCE.getRegisteredHandler(method, target);

            if (registeredHandler != null) {
                registeredHandler.setStatus(registeredHandler.getStatus().value() ? Register.Switch.OFF : Register.Switch.ON);
                result.setStatusMsg("Switch handler (method: [" + method + "] target: [" + target + "]) to " + registeredHandler.getStatus().name());
            } else {
                result.setStatusMsg("Unknown handler (method: [" + method + "] target: [" + target + "])");
            }
        }
        result.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        return new GsonParser(result);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
