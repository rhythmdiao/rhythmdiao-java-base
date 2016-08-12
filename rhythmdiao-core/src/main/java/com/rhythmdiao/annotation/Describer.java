package com.rhythmdiao.annotation;

import java.lang.annotation.*;

/**
 * request describer annotation
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Describer {
    String comment() default "";
}
