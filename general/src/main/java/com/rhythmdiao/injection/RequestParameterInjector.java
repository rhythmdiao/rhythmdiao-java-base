package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.annotation.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

@Injector
public final class RequestParameterInjector extends AbstractInjector {
    public RequestParameterInjector() {
        super(RequestParameter.class);
    }

    public void injectField(Map<Field, Class<? extends Annotation>> annotatedFields, HttpServletRequest request, Map<String, Object> fieldMap) {
        for (Map.Entry<Field, Class<? extends Annotation>> entry :
                Maps.filterValues(annotatedFields, new Predicate<Class<? extends Annotation>>() {
                    public boolean apply(Class<? extends Annotation> annotation) {
                        return annotation.equals(RequestParameter.class);
                    }
                }).entrySet()) {
            String field = entry.getKey().getAnnotation(RequestParameter.class).value();
            field = Strings.isNullOrEmpty(field) ? entry.getKey().getName() : field;
            final String value = request.getParameter(field);
            if (!Strings.isNullOrEmpty(value)) {
                fieldMap.put(field, value);
            }
        }
    }
}
