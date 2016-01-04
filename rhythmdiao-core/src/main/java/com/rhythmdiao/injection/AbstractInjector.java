package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.util.TypeConverter;
import javafx.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public abstract class AbstractInjector implements Injector {
    private Class<? extends Annotation> annotation;

    protected AbstractInjector(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Class<? extends Annotation> getAnnotation() {
        return this.annotation;
    }

    protected abstract Pair<String, String> extract(Field field, HttpServletRequest request);

    public void injectField(Field field, HttpServletRequest request, Map<String, Object> fieldMap) {
        Pair<String, String> source = extract(field, request);
        if (!Strings.isNullOrEmpty(source.getValue())) {
            Object value;
            if (field.getAnnotation(DateTimeFormat.class) != null) {
                value = TypeConverter.convert(source.getValue(), field.getType(), field.getAnnotation(DateTimeFormat.class).pattern());
            } else {
                value = TypeConverter.convert(source.getValue(), field.getType());
            }
            if (value != null) {
                fieldMap.put(source.getKey(), value);
            }
        }
    }
}
