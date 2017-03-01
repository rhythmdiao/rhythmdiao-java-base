package com.rhythmdiao.annotation;

import java.lang.annotation.*;

/**
 * request handler annotation
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestfulHandler {
    String target();

    String method() default GET;

    String identification() default "";

    String description();

    int cache() default 0;

    String GET = "GET";
    String POST = "POST";
}
