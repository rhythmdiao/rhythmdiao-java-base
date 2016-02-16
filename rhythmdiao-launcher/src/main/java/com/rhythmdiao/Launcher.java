package com.rhythmdiao;

import com.rhythmdiao.util.ApplicationContextWrapper;
import com.rhythmdiao.util.time.TimeCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Launcher {
    private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

    public static void main(String args[]) throws Exception {
        TimeCounter interval = new TimeCounter().start();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-config.xml");
        ApplicationContextWrapper applicationContextWrapper = applicationContext.getBean(ApplicationContextWrapper.class);
        applicationContextWrapper.setApplicationContext(applicationContext);
        LOG.info("Launching took {}ms", interval.end());
    }
}
