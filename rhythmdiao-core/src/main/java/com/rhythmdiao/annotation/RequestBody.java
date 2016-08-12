package com.rhythmdiao.annotation;

import java.lang.annotation.*;

/**
 * request body annotation
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestBody {
    String value() default "";
}
