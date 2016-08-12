package com.rhythmdiao.annotation;

import java.lang.annotation.*;

/**
 * request header annotation
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestHeader {
    String value() default "";
}
