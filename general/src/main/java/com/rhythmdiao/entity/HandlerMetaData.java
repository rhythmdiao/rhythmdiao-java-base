package com.rhythmdiao.entity;

import com.google.gson.annotations.Expose;
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

public final class HandlerMetaData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Expose
    private String uri;
    @Expose
    private String method;
    @Expose
    private String description;
    @Expose
    private Map<String, String> parameters;

    private BaseHandler handler;
    private Map<Field, Class<? extends Annotation>> annotatedFields;

    public HandlerMetaData(BaseHandler handler, int fieldSize) {
        RestfulHandler annotation = handler.getClass().getAnnotation(RestfulHandler.class);
        this.method = annotation.method();
        this.uri = annotation.uri();
        this.description = annotation.description();
        this.handler = handler;
        this.annotatedFields = new HashMap<Field, Class<? extends Annotation>>(fieldSize);
        this.parameters = new HashMap<String, String>(fieldSize);
    }

    public void putFields(Field[] fields) {
        for (Field field : fields) {
            for (Class<? extends AbstractInjector> cls : FieldInjection.INSTANCE.getInjectorList()) {
                try {
                    final Class<? extends Annotation> annotation = cls.newInstance().getAnnotation();
                    if (field.isAnnotationPresent(annotation)) {
                        annotatedFields.put(field, annotation);
                        this.parameters.put(field.getName(), field.getType().getSimpleName() + ";@" + annotation.getSimpleName());
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<Field, Class<? extends Annotation>> getAnnotatedFields() {
        return Collections.unmodifiableMap(annotatedFields);
    }
}
