package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.utils.TypeConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

@Injector
public final class RequestHeaderInjector extends AbstractInjector {
    public RequestHeaderInjector() {
        super(RequestHeader.class);
    }

    public void injectField(Map<Field, Class<? extends Annotation>> annotatedFieldMap, HttpServletRequest request, Map<String, Object> fieldMap) {
        for (Map.Entry<Field, Class<? extends Annotation>> entry :
                Maps.filterValues(annotatedFieldMap, new Predicate<Class<? extends Annotation>>() {
                    public boolean apply(Class<? extends Annotation> annotation) {
                        return annotation.equals(RequestHeader.class);
                    }
                }).entrySet()) {
            Field field = entry.getKey();
            String key = field.getAnnotation(RequestHeader.class).value();
            key = Strings.isNullOrEmpty(key) ? field.getName() : key;
            String source = request.getHeader(key);
            if (!Strings.isNullOrEmpty(source)) {
                Object value;
                if (field.getAnnotation(DateTimeFormat.class) != null) {
                    value = TypeConverter.convert(source, field.getType(), field.getAnnotation(DateTimeFormat.class).pattern());
                } else {
                    value = TypeConverter.convert(source, field.getType());
                }
                if (value != null) {
                    fieldMap.put(key, value);
                }
            }
        }
    }
}
