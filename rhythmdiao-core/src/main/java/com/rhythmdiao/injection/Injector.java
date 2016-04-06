package com.rhythmdiao.injection;

import com.rhythmdiao.constant.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public interface Injector {
    Logger LOG = LoggerFactory.getLogger(LoggerName.INJECTOR);

    void injectField(Field field, HttpServletRequest request, Map<String, Object> fieldMap);

    void describeParam(Field field, String param);

    Object convertParam(Field filed, String param);
}
