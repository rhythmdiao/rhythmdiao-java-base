package com.rhythmdiao.utils;

import org.springframework.aop.framework.AdvisedSupport;

import java.lang.reflect.Field;

public class AopUtil {
    @SuppressWarnings("unchecked")
    public static <T> T getCglibProxyTargetObject(T proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return (T) ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }
}
