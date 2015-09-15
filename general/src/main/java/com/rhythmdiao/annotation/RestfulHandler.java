package com.rhythmdiao.annotation;

import org.eclipse.jetty.http.HttpMethods;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestfulHandler {
    String uri();

    String method() default HttpMethods.GET;

    String identification() default "";

    String description();
}
