package com.rhythmdiao.handler;

import com.rhythmdiao.annotation.RestfulHandler;
import com.rhythmdiao.constant.LoggerName;
import com.rhythmdiao.entity.HandlerMetaData;
import com.rhythmdiao.util.ApplicationContextWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.List;
import java.util.Set;

public final class Initializer {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerName.INITIALIZER);

    private List<String> pkgs;

    private List<String> ignored;

    public void setPkgs(List<String> pkgs) {
        this.pkgs = pkgs;
    }

    public void setIgnored(List<String> ignored) {
        this.ignored = ignored;
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
                } catch (ClassNotFoundException ignored) {
                }
            }
        }

        for (String target : ignored) {
            HandlerPath.INSTANCE.setIgnored(target);
        }
    }

    private void dispatcher(Class cls) throws ClassNotFoundException {
        RestfulHandler annotation = (RestfulHandler) cls.getAnnotation(RestfulHandler.class);
        String method = annotation.method();
        String uri = annotation.target();
        BaseHandler handler = (BaseHandler) ApplicationContextWrapper.getBean(cls);
        HandlerMetaData metaData = new HandlerMetaData(annotation, cls.getDeclaredFields().length);
        metaData.putFields(cls.getDeclaredFields());
        HandlerPath.INSTANCE.setPathMap(method, uri, handler.getClass(), metaData);
        LOG.info(String.format("Dispatching [%s, %s] on handler: %s", method, uri, cls.getSimpleName()));
    }
}
