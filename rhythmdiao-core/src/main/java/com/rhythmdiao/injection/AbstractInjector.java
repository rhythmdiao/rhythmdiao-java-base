package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.Describer;
import com.rhythmdiao.TypeConverter;
import com.rhythmdiao.handler.BaseHandler;
import com.rhythmdiao.util.LogUtil;
import javafx.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class AbstractInjector implements Injector {
    private Class<? extends Annotation> annotation;

    protected AbstractInjector(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Class<? extends Annotation> getAnnotation() {
        return this.annotation;
    }

    protected abstract Pair<String, String> extract(Field field, HttpServletRequest request);

    public void injectField(BaseHandler handler, Field field, HttpServletRequest request) {
        Pair<String, String> source = extract(field, request);
        if (!Strings.isNullOrEmpty(source.getValue())) {
            describeParam(field, source.getValue());
            Object value = convertParam(field, source.getValue());
            if (value != null) {
                try {
                    field.setAccessible(true);
                    field.set(handler, value);
                } catch (IllegalAccessException e) {
                    LogUtil.error(LOG, "Failed to inject field {}, and error is {}", field.getName(), e.getMessage());
                }
            }
        }
    }

    public void describeParam(Field field, String param) {
        if (field.getAnnotation(Describer.class) != null) {
            LogUtil.debug(LOG, "{}:{}={}", field.getAnnotation(Describer.class).comment(), field.getName(), param);
        }
    }

    public Object convertParam(Field field, String param) {
        DateTimeFormat format = field.getAnnotation(DateTimeFormat.class);
        return TypeConverter.convert(param, field.getType(), format != null ? format.pattern() : null);
    }
}
