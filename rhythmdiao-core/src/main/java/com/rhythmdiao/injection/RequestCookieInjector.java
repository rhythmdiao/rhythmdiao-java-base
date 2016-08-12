package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.CookieParameter;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.util.CookieUtil;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Injector
public final class RequestCookieInjector extends AbstractInjector {
    public RequestCookieInjector() {
        super(CookieParameter.class);
    }

    @Override
    protected Pair<String, String> extract(Field field, HttpServletRequest request) {
        CookieParameter cookie = field.getAnnotation(CookieParameter.class);
        String key = cookie.value();
        key = Strings.isNullOrEmpty(key) ? field.getName() : key;
        String source = new CookieUtil(request, null, cookie.domain(), cookie.path()).getCookie(key);
        return new Pair<String, String>(key, source);
    }
}
