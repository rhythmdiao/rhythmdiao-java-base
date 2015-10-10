package com.rhythmdiao.test;

import com.google.common.collect.Maps;
import com.rhythmdiao.RequestPath;
import com.rhythmdiao.handler.BaseHandler;
import com.rhythmdiao.injection.FieldInjection;
import com.rhythmdiao.result.AbstractResult;
import org.junit.After;
import org.junit.Before;
import org.skife.config.cglib.beans.BeanMap;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public abstract class AbstractTest extends AbstractJUnit4SpringContextTests {
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;

    public AbstractTest() {
    }

    @Before
    public void init() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    protected String handle(String method, String target) {
        BaseHandler handler = (BaseHandler) RequestPath.INSTANCE.getPathMap().row(method).get(target);
        if (handler != null) {
            try {
                BaseHandler baseHandler = (BaseHandler) Class.forName(handler.getClass().getName()).newInstance();
                baseHandler.setHandlerMetaData(handler.getHandlerMetaData());
                Map<Field, Class<? extends Annotation>> annotatedFields = handler.getHandlerMetaData().getAnnotatedFields();
                Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(annotatedFields.size());
                FieldInjection.INSTANCE.injectField(annotatedFields, request, fieldMap);
                BeanMap beanMap = BeanMap.create(baseHandler);
                beanMap.putAll(fieldMap);
                AbstractResult result = baseHandler.execute();
                return result.specificTo();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @After
    public void destroy() {
    }
}
