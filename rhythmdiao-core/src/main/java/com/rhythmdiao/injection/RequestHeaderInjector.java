package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.annotation.RequestHeader;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Injector
public final class RequestHeaderInjector extends AbstractInjector {
    public RequestHeaderInjector() {
        super(RequestHeader.class);
    }

    @Override
    protected Pair<String, String> extract(Field field, HttpServletRequest request) {
        String key = field.getAnnotation(RequestHeader.class).value();
        key = Strings.isNullOrEmpty(key) ? field.getName() : key;
        String source = request.getHeader(key);
        return new Pair<String, String>(key, source);
    }
}
