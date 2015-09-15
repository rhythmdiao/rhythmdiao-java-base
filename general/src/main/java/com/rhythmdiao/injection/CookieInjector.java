package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.CookieParameter;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.utils.CookieUtil;
import com.rhythmdiao.utils.TypeConverter;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

@Injector
public final class CookieInjector extends AbstractInjector {
    public CookieInjector() {
        super(CookieParameter.class);
    }

    public void injectField(Map<Field, Class<? extends Annotation>> annotatedFieldMap, HttpServletRequest request, Map<String, Object> fieldMap) {
        for (Map.Entry<Field, Class<? extends Annotation>> entry :
                Maps.filterValues(annotatedFieldMap, new Predicate<Class<? extends Annotation>>() {
                    public boolean apply(Class<? extends Annotation> annotation) {
                        return annotation.equals(CookieParameter.class);
                    }
                }).entrySet()) {
            CookieParameter cookie = entry.getKey().getAnnotation(CookieParameter.class);
            String key = cookie.value();
            String field = entry.getKey().getName();
            key = Strings.isNullOrEmpty(key) ? field : key;
            final String value = new CookieUtil(request, null, cookie.domain(), cookie.path()).getCookie(key);
            if (!Strings.isNullOrEmpty(value)) {
                fieldMap.put(field, TypeConverter.convert(value, entry.getKey().getType()));
            }
        }
    }
}
