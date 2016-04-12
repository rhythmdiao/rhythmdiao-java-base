package com.rhythmdiao.injection;

import com.rhythmdiao.constant.LoggerName;
import com.rhythmdiao.handler.BaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public interface Injector {
    Logger LOG = LoggerFactory.getLogger(LoggerName.INJECTOR);

    void injectField(BaseHandler handler, Field field, HttpServletRequest request);

    void describeParam(Field field, String param);

    Object convertParam(Field filed, String param);
}
