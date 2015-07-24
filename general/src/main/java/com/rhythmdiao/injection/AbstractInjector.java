package com.rhythmdiao.injection;

import java.lang.annotation.Annotation;

public abstract class AbstractInjector implements Injector {
    public abstract Class<? extends Annotation> getAnnotation();
}
