package com.rhythmdiao.entity;

import com.google.gson.annotations.Expose;
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
    private BaseHandler handler;

    @Expose
    private String name;
    @Expose
    private String method;
    @Expose
    private String uri;

    private Map<Field, Class<? extends Annotation>> annotatedFields;
    @Expose
    private Map<String, String> fields;

    public HandlerMetaData(BaseHandler handler, String method, String uri, int fieldSize) {
        this.name = handler.getClass().getSimpleName();
        this.method = method;
        this.uri = uri;
        this.handler = handler;
        this.annotatedFields = new HashMap<Field, Class<? extends Annotation>>(fieldSize);
        this.fields = new HashMap<String, String>(fieldSize);
    }

    public void putAnnotatedFields(Field[] fields) {
        for (Field field : fields) {
            for (Class<? extends AbstractInjector> cls : FieldInjection.INSTANCE.getInjectorList()) {
                try {
                    final Class<? extends Annotation> annotation = cls.newInstance().getAnnotation();
                    if (field.isAnnotationPresent(annotation)) {
                        this.annotatedFields.put(field, annotation);
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        putFieldsDescription();
    }

    private void putFieldsDescription() {
        for (Map.Entry<Field, Class<? extends Annotation>> field : getAnnotatedFields().entrySet()) {
            fields.put(field.getKey().getName(), field.getKey().getType().getSimpleName() + ";@" + field.getValue().getSimpleName());
        }
    }

    public Map<Field, Class<? extends Annotation>> getAnnotatedFields() {
        return Collections.unmodifiableMap(annotatedFields);
    }
}
