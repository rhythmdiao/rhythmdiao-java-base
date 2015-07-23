package com.rhythmdiao.injection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.annotation.RequestParameter;

import java.lang.annotation.Annotation;
import java.util.List;

public enum FieldAnnotationStorage {
    INSTANCE;

    private final List<Class<? extends Annotation>> fieldAnnotationList;

    FieldAnnotationStorage() {
        fieldAnnotationList = Lists.newArrayListWithExpectedSize(2);
        fieldAnnotationList.add(RequestHeader.class);
        fieldAnnotationList.add(RequestParameter.class);
    }

    public ImmutableList<Class<? extends Annotation>> getFieldAnnotationList() {
        return ImmutableList.copyOf(fieldAnnotationList);
    }
}
