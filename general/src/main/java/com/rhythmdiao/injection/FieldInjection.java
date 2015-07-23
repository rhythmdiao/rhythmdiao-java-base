package com.rhythmdiao.injection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rhythmdiao.handlers.HandlerInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public enum FieldInjection {
    INSTANCE;

    private final List<Class<? extends AbstractInjection>> fieldInjectionList;

    FieldInjection() {
        fieldInjectionList = Lists.newArrayListWithExpectedSize(2);
        fieldInjectionList.add(RequestHeaderInjection.class);
        fieldInjectionList.add(RequestParameterInjection.class);
    }

    public ImmutableList<Class<? extends AbstractInjection>> getFieldInjectionList() {
        return ImmutableList.copyOf(fieldInjectionList);
    }

    public Map<String, Object> injectField(HandlerInfo handlerInfo, HttpServletRequest request) throws IllegalAccessException, InstantiationException {
        Map<String, Object> injectedFieldMap = Maps.newHashMap();
        for (Class<? extends AbstractInjection> fieldInjection : getFieldInjectionList()) {
            fieldInjection.newInstance().injectField(handlerInfo, request, injectedFieldMap);
        }
        return injectedFieldMap;
    }
}
