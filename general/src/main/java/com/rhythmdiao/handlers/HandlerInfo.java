package com.rhythmdiao.handlers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public final class HandlerInfo {
    private Map<Field, Class<? extends Annotation>> annotatedFieldMap;

    public HandlerInfo(int expectedSize) {
        annotatedFieldMap = Maps.newHashMapWithExpectedSize(expectedSize);
    }

    public void setAnnotatedFieldMap(Field field, Class<? extends Annotation> annotation) {
        annotatedFieldMap.put(field, annotation);
    }

    public ImmutableMap<Field, Class<? extends Annotation>> getAnnotatedFieldMap() {
        return ImmutableMap.copyOf(annotatedFieldMap);
    }
}
