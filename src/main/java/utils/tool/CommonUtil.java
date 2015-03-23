package utils.tool;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.reflections.Reflections;
import utils.config.ApplicationContextWrapper;

import java.lang.annotation.Annotation;
import java.util.Set;

public class CommonUtil {
    public static Set<Class<?>> AnnotatedWith(Class<? extends Annotation> annotation) {
        Reflections reflections = ApplicationContextWrapper.getBean("reflections", Reflections.class);
        return reflections.getTypesAnnotatedWith(annotation);
    }

    public static String objectToXml(Object object) {
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.autodetectAnnotations(true);
        return xStream.toXML(object);
    }
}
