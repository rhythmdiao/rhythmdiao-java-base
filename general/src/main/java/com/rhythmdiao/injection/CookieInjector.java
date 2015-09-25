package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.rhythmdiao.annotation.CookieParameter;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.utils.CookieUtil;
import com.rhythmdiao.utils.TypeConverter;
import org.springframework.format.annotation.DateTimeFormat;

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
            Field field = entry.getKey();
            CookieParameter cookie = field.getAnnotation(CookieParameter.class);
            String key = cookie.value();
            key = Strings.isNullOrEmpty(key) ? field.getName() : key;
            final String source = new CookieUtil(request, null, cookie.domain(), cookie.path()).getCookie(key);
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
