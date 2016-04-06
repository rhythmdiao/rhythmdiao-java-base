package com.rhythmdiao.injection;

import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
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

    private final List<AbstractInjector> injectorList;

    FieldInjection() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Injector.class));
        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com.rhythmdiao.injection");
        injectorList = new ArrayList<AbstractInjector>(beanDefinitions.size());
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                Class cls = Class.forName(beanDefinition.getBeanClassName());
                if (cls != null && cls.getSuperclass().equals(AbstractInjector.class)) {
                    injectorList.add((AbstractInjector) cls.newInstance());
                }
            } catch (Throwable t) {
                Throwables.propagate(t);
            }
        }
    }

    public List<AbstractInjector> getInjectorList() {
        return Collections.unmodifiableList(injectorList);
    }

    public void injectField(Map<Field, Class<? extends Annotation>> annotatedFields, HttpServletRequest request, Map<String, Object> fieldMap) {
        for (final AbstractInjector injector : injectorList) {
            for (Map.Entry<Field, Class<? extends Annotation>> entry :
                    Maps.filterValues(annotatedFields, new Predicate<Class<? extends Annotation>>() {
                        public boolean apply(Class<? extends Annotation> annotation) {
                            return annotation.equals(injector.getAnnotation());
                        }
                    }).entrySet()) {
                injector.injectField(entry.getKey(), request, fieldMap);
            }
        }
    }
}
