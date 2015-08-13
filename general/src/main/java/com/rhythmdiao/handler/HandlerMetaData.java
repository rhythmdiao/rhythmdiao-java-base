package com.rhythmdiao.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class HandlerMetaData {
    private Map<Field, Class<? extends Annotation>> annotatedFields;

    public HandlerMetaData(int expectedSize) {
        annotatedFields = new HashMap<Field, Class<? extends Annotation>>(expectedSize);
    }

    public void putAnnotatedFields(Field field, Class<? extends Annotation> annotation) {
        annotatedFields.put(field, annotation);
    }

    public Map<Field, Class<? extends Annotation>> getAnnotatedFields() {
        return Collections.unmodifiableMap(annotatedFields);
    }
}
