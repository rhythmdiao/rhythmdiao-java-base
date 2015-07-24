package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

@Injector
public final class RequestHeaderInjector extends AbstractInjector {
    public Class<RequestHeader> getAnnotation() {
        return RequestHeader.class;
    }

    public void injectField(ImmutableMap<Field, Class<? extends Annotation>> annotatedFieldMap, HttpServletRequest request, Map<String, Object> fieldMap) {
        for (ImmutableMap.Entry<Field, Class<? extends Annotation>> entry :
                Maps.filterValues(annotatedFieldMap, new Predicate<Class<? extends Annotation>>() {
                    public boolean apply(Class<? extends Annotation> annotation) {
                        return annotation.equals(RequestHeader.class);
                    }
                }).entrySet()) {
            String field = entry.getKey().getAnnotation(RequestHeader.class).value();
            field = Strings.isNullOrEmpty(field) ? entry.getKey().getName() : field;
            final String value = request.getHeader(field);
            if (!Strings.isNullOrEmpty(value)) {
                fieldMap.put(field, value);
            }
        }
    }
}
