package com.rhythmdiao.injection;

import com.rhythmdiao.annotation.Injector;
import org.reflections.Reflections;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public enum FieldInjection {
    INSTANCE;

    private final List<Class<? extends AbstractInjector>> injectorList;

    @SuppressWarnings("unchecked")
    FieldInjection() {
        Reflections reflections = new Reflections("com.rhythmdiao.injection");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Injector.class);
        injectorList = new ArrayList<Class<? extends AbstractInjector>>(classes.size());
        for (Class<?> cls : classes) {
            if (cls.getSuperclass().equals(AbstractInjector.class)) {
                final Class<? extends AbstractInjector> injector = (Class<? extends AbstractInjector>) cls;
                injectorList.add(injector);
            }
        }
    }

    public List<Class<? extends AbstractInjector>> getInjectorList() {
       return Collections.unmodifiableList(injectorList);
    }

    public void injectField(Map<Field, Class<? extends Annotation>> annotatedFields, HttpServletRequest request, Map<String, Object> fieldMap) throws IllegalAccessException, InstantiationException {
        for (Class<? extends AbstractInjector> fieldInjector : getInjectorList()) {
            fieldInjector.newInstance().injectField(annotatedFields, request, fieldMap);
        }
    }
}
