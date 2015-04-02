package annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterInfo {
    String author() default "";
    String description() default "";
    String date() default "";
}
