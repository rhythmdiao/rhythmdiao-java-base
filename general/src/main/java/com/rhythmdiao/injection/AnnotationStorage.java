package com.rhythmdiao.injection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.annotation.RequestParameter;

import java.lang.annotation.Annotation;
import java.util.List;

public enum AnnotationStorage {
    instance;

    private final List<Class<? extends Annotation>> customAnnotationList;

    AnnotationStorage() {
        customAnnotationList = Lists.newArrayListWithExpectedSize(2);
        customAnnotationList.add(RequestHeader.class);
        customAnnotationList.add(RequestParameter.class);
    }

    public ImmutableList<Class<? extends Annotation>> getCustomAnnotationList() {
        return ImmutableList.copyOf(customAnnotationList);
    }

    public static AnnotationStorage getInstance() {
        return instance;
    }
}
