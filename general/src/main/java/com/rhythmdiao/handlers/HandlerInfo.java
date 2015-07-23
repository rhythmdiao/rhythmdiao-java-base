package com.rhythmdiao.handlers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class HandlerInfo {
    private Map<Class<? extends Annotation>, List<FieldWithAnnotation>> fieldwithAnnotationMap = Maps.newLinkedHashMap();

    public Map<Class<? extends Annotation>, List<FieldWithAnnotation>> getFieldwithAnnotationMap() {
        return fieldwithAnnotationMap;
    }

    public void setFieldwithAnnotationMap(Class<? extends Annotation> annotation, FieldWithAnnotation fieldWithAnnotation) {
        List<FieldWithAnnotation> fieldWithAnnotationList = fieldwithAnnotationMap.get(annotation);
        if (fieldWithAnnotationList == null) {
            fieldWithAnnotationList = Lists.newArrayList();
            fieldwithAnnotationMap.put(annotation, fieldWithAnnotationList);
        }
        fieldWithAnnotationList.add(fieldWithAnnotation);
    }

    public class FieldWithAnnotation {
        public Field getField() {
            return field;
        }

        public Annotation getAnnotation() {
            return annotation;
        }

        private Field field;
        private Annotation annotation;

        public FieldWithAnnotation(Field field, Annotation annotation) {
            super();
            this.field = field;
            this.annotation = annotation;
        }
    }
}
