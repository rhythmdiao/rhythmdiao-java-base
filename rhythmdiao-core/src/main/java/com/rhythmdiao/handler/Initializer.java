package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.util.ApplicationContextWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.List;
import java.util.Set;

public class Initializer {
    private static final Logger LOG = LoggerFactory.getLogger("handler_initializer");

    private List<String> pkgs;

    public void setPkgs(List<String> pkgs) {
        this.pkgs = pkgs;
    }

    public void init() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RestfulHandler.class));
        for (String pkg : pkgs) {
            Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents(pkg);
            for (BeanDefinition beanDefinition : beanDefinitions) {
                Class cls;
                try {
                    cls = Class.forName(beanDefinition.getBeanClassName());
                    dispatcher(cls);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void dispatcher(Class cls) throws ClassNotFoundException {
        RestfulHandler annotation = (RestfulHandler) cls.getAnnotation(RestfulHandler.class);
        String method = annotation.method();
        String uri = annotation.target();
        BaseHandler handler = (BaseHandler) ApplicationContextWrapper.getBean(cls);
        HandlerMetaData handlerMetaData = new HandlerMetaData(handler, cls.getDeclaredFields().length);
        handlerMetaData.putFields(cls.getDeclaredFields());
        handler.setHandlerMetaData(handlerMetaData);
        HandlerPath.INSTANCE.setPathMap(method, uri, handler);
        LOG.info(String.format("Dispatching [%s, %s] on handler: %s", method, uri, cls.getSimpleName()));
    }
}
