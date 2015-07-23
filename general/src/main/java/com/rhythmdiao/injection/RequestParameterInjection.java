package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.RequestParameter;
import com.rhythmdiao.handlers.HandlerInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public final class RequestParameterInjection extends AbstractInjection {
    public void injectField(HandlerInfo handlerInfo, HttpServletRequest request, Map<String, Object> injectedFieldMap) {
        final List<HandlerInfo.FieldWithAnnotation> fieldWithAnnotationList = handlerInfo.getFieldwithAnnotationMap().get(RequestParameter.class);
        for (HandlerInfo.FieldWithAnnotation fieldWithAnnotation : fieldWithAnnotationList) {
            final RequestParameter requestParameter = (RequestParameter) fieldWithAnnotation.getAnnotation();
            String field = requestParameter.value();
            if (Strings.isNullOrEmpty(field)) {
                field = fieldWithAnnotation.getField().getName();
            }
            final String value = request.getParameter(field);
            if (!Strings.isNullOrEmpty(value)) {
                injectedFieldMap.put(field, value);
            }
        }
    }
}
