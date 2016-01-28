package com.rhythmdiao.injection;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public interface Injector {
    void injectField(Field field, HttpServletRequest request, Map<String, Object> fieldMap);

    void describeParam(Field field, String param);

    Object convertParam(Field filed, String param);
}
