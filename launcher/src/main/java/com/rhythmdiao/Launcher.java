package com.rhythmdiao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.rhythmdiao.utils.config.ApplicationContextWrapper;

public final class Launcher {
    private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

    public static void main(String args[]) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-config.xml");
        ApplicationContextWrapper applicationContextWrapper = applicationContext.getBean(ApplicationContextWrapper.class);
        applicationContextWrapper.setApplicationContext(applicationContext);
        LOG.info("Launching......");
    }
}
