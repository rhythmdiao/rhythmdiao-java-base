package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.Describer;
import com.rhythmdiao.constant.LoggerName;
import com.rhythmdiao.TypeConverter;
import com.rhythmdiao.util.LogUtil;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
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
            describeParam(field, source.getValue());
            Object value = convertParam(field, source.getValue());
            if (value != null) {
                fieldMap.put(source.getKey(), value);
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
