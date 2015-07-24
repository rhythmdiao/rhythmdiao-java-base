package com.rhythmdiao.injection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.rhythmdiao.annotation.Injector;
import org.reflections.Reflections;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

public enum FieldInjection {
    INSTANCE;

    private final List<Class<? extends AbstractInjector>> injectorList;

    @SuppressWarnings("unchecked")
    FieldInjection() {
        Reflections reflections = new Reflections("com.rhythmdiao.injection");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Injector.class);
        injectorList = Lists.newArrayListWithExpectedSize(classes.size());
        for (Class<?> clazz : classes) {
            if (clazz.getSuperclass().equals(AbstractInjector.class)) {
                final Class<? extends AbstractInjector> injector = (Class<? extends AbstractInjector>) clazz;
                injectorList.add(injector);
            }
        }
    }

    public ImmutableList<Class<? extends AbstractInjector>> getInjectorList() {
        return ImmutableList.copyOf(injectorList);
    }

    public void injectField(ImmutableMap<Field, Class<? extends Annotation>> annotatedFieldMap, HttpServletRequest request, Map<String, Object> fieldMap) throws IllegalAccessException, InstantiationException {
        for (Class<? extends AbstractInjector> fieldInjector : getInjectorList()) {
            fieldInjector.newInstance().injectField(annotatedFieldMap, request, fieldMap);
        }
    }
}
