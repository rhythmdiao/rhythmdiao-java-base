package com.rhythmdiao.annotation;

import java.lang.annotation.*;

/**
 * request parameter annotation
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {
    String value() default "";
}
