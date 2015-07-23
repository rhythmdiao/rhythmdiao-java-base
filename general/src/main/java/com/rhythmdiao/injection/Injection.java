package com.rhythmdiao.injection;

import com.rhythmdiao.handlers.HandlerInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface Injection {
    void injectField(HandlerInfo handlerInfo, HttpServletRequest request, Map<String, Object> injectedFieldMap);
}
