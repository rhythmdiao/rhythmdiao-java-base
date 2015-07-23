package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public final class RequestParameterInjection extends AbstractInjection {
    public void injectField(ImmutableMap<Field, Class<? extends Annotation>> annotatedFieldMap, HttpServletRequest request, Map<String, Object> fieldMap) {
        for (ImmutableMap.Entry<Field, Class<? extends Annotation>> entry :
                Maps.filterValues(annotatedFieldMap, new Predicate<Class<? extends Annotation>>() {
                    @Override
                    public boolean apply(Class<? extends Annotation> annotation) {
                        return annotation == RequestParameter.class;
                    }
                }).entrySet()) {
            String field = entry.getKey().getAnnotation(RequestParameter.class).value();
            if (Strings.isNullOrEmpty(field)) {
                field = entry.getKey().getName();
            }
            final String value = request.getParameter(field);
            if (!Strings.isNullOrEmpty(value)) {
                fieldMap.put(field, value);
            }
        }
    }
}
