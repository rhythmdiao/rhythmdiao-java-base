package util.annotation.rest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mayuxing on 2014/12/18.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RestfulServlet {
    String URI();

    String Method();
}
