package com.rhythmdiao.annotation;

import java.lang.annotation.*;

/**
 * request cookie annotation
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CookieParameter {
    String value() default "";

    String domain() default "";

    String path() default "/";
}
