package com.rhythmdiao.injection;

import java.lang.annotation.Annotation;

public abstract class AbstractInjector implements Injector {
    private Class<? extends Annotation> annotation;

    public AbstractInjector(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Class<? extends Annotation> getAnnotation() {
        return this.annotation;
    }
}
