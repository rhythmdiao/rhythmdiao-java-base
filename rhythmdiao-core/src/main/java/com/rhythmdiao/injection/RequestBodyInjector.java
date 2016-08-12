package com.rhythmdiao.injection;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.rhythmdiao.annotation.Injector;
import com.rhythmdiao.annotation.RequestBody;
import com.rhythmdiao.util.ConstResult;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

@Injector
public final class RequestBodyInjector extends AbstractInjector {
    public RequestBodyInjector() {
        super(RequestBody.class);
    }

    @Override
    protected Pair<String, String> extract(Field field, HttpServletRequest request) {
        String key = field.getAnnotation(RequestBody.class).value();
        key = Strings.isNullOrEmpty(key) ? field.getName() : key;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), Charsets.UTF_8.name()));
            String source = ConstResult.STRING.getEmpty();
            String line;
            while ((line = reader.readLine()) != null) {
                source += line;
            }
            reader.close();
            return new Pair<String, String>(key, source);
        } catch (IOException ignored) {
        }
        return null;
    }
}
