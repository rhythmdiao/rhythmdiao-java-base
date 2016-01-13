package com.rhythmdiao;

import com.rhythmdiao.util.IntervalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.rhythmdiao.util.ApplicationContextWrapper;

public final class Launcher {
    private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

    public static void main(String args[]) throws Exception {
        IntervalUtil interval = new IntervalUtil().start();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-config.xml");
        ApplicationContextWrapper applicationContextWrapper = applicationContext.getBean(ApplicationContextWrapper.class);
        applicationContextWrapper.setApplicationContext(applicationContext);
        LOG.info("Launching took {}ms", interval.end());
    }
}
