package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.utils.TypeConverter;

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
            String key = entry.getKey().getAnnotation(RequestHeader.class).value();
            String field = entry.getKey().getName();
            key = Strings.isNullOrEmpty(key) ? field : key;
            final String value = request.getHeader(key);
            if (!Strings.isNullOrEmpty(value)) {
                fieldMap.put(field, TypeConverter.convert(value, entry.getKey().getType()));
            }
        }
    }
}
