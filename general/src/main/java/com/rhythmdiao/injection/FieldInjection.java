package com.rhythmdiao.injection;

import com.rhythmdiao.annotation.Injector;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public enum FieldInjection {
    INSTANCE;

    private final List<Class<? extends AbstractInjector>> injectorList;

    @SuppressWarnings("unchecked")
    FieldInjection() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Injector.class));
        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com.rhythmdiao.injection");
        injectorList = new ArrayList<Class<? extends AbstractInjector>>(beanDefinitions.size());
        for (BeanDefinition beanDefinition : beanDefinitions) {
            Class cls = null;
            try {
                cls = Class.forName(beanDefinition.getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (cls != null && cls.getSuperclass().equals(AbstractInjector.class)) {
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
