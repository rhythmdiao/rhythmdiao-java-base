package com.rhythmdiao;

import com.rhythmdiao.util.time.TimeCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Launcher {
    private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

    public static void main(String args[]) throws Exception {
        TimeCounter interval = new TimeCounter().start();
        new ClassPathXmlApplicationContext("classpath*:spring-config.xml");
        LOG.info("Launching took {}ms", interval.end());
    }
}
