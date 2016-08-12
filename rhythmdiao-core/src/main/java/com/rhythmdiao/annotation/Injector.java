package com.rhythmdiao.annotation;

import java.lang.annotation.*;

/**
 * request injector annotation
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Injector {
}
