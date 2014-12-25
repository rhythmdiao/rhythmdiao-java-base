package util.annotation.rest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RestfulServlet {
    String URI();

    String Method();
}
