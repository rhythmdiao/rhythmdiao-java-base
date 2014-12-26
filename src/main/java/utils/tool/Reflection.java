package utils.tool;

import org.reflections.Reflections;
import utils.config.ApplicationContextWrapper;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by mayuxing on 2014/12/19.
 */
public class Reflection {
    public static Set<Class<?>> AnnotatedWith(Class<? extends Annotation> annotation) {
        Reflections reflections = ApplicationContextWrapper.getBean("reflections", Reflections.class);
        return reflections.getTypesAnnotatedWith(annotation);
    }
}
