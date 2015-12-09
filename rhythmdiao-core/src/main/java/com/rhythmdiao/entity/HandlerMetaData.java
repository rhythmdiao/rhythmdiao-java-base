package com.rhythmdiao.entity;

import com.google.gson.annotations.JsonAdapter;
import com.rhythmdiao.adapter.HandlerMetaDataAdapter;
import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.handler.BaseHandler;
import com.rhythmdiao.injection.AbstractInjector;
import com.rhythmdiao.injection.FieldInjection;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@JsonAdapter(HandlerMetaDataAdapter.class)
public final class HandlerMetaData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String target;
    private String method;
    private String description;
    private Map<Field, Class<? extends Annotation>> annotatedFields;

    public HandlerMetaData(BaseHandler handler, int fieldSize) {
        RestfulHandler annotation = handler.getClass().getAnnotation(RestfulHandler.class);
        this.method = annotation.method();
        this.target = annotation.target();
        this.description = annotation.description();
        this.annotatedFields = new HashMap<Field, Class<? extends Annotation>>(fieldSize);
    }

    public void putFields(Field[] fields) {
        for (Field field : fields) {
            for (AbstractInjector injector : FieldInjection.INSTANCE.getInjectorList()) {
                final Class<? extends Annotation> annotation = injector.getAnnotation();
                if (field.isAnnotationPresent(annotation)) {
                    annotatedFields.put(field, annotation);
                }
            }
        }
    }

    public Map<Field, Class<? extends Annotation>> getAnnotatedFields() {
        return Collections.unmodifiableMap(annotatedFields);
    }

    public String getTarget() {
        return target;
    }

    public String getMethod() {
        return method;
    }

    public String getDescription() {
        return description;
    }
}
