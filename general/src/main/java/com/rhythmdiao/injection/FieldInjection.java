package com.rhythmdiao.injection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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

    public void injectField(ImmutableMap<Field, Class<? extends Annotation>> annotatedFieldMap, HttpServletRequest request, Map<String, Object> fieldMap) throws IllegalAccessException, InstantiationException {
        for (Class<? extends AbstractInjection> fieldInjection : getFieldInjectionList()) {
            fieldInjection.newInstance().injectField(annotatedFieldMap, request, fieldMap);
        }
    }
}
