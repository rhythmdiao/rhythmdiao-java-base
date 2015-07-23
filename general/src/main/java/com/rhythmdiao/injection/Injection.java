package com.rhythmdiao.injection;

import com.google.common.collect.ImmutableMap;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public interface Injection {
    void injectField(ImmutableMap<Field, Class<? extends Annotation>> annotatedFieldMap, HttpServletRequest request, Map<String, Object> fieldMap);
}
