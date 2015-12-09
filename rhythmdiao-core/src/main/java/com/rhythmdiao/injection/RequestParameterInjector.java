package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.annotation.RequestParameter;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Injector
public final class RequestParameterInjector extends AbstractInjector {
    public RequestParameterInjector() {
        super(RequestParameter.class);
    }

    @Override
    protected Pair<String, String> extract(Field field, HttpServletRequest request) {
        String key = field.getAnnotation(RequestParameter.class).value();
        key = Strings.isNullOrEmpty(key) ? field.getName() : key;
        String source = request.getParameter(key);
        return new Pair<String, String>(key, source);
    }
}
