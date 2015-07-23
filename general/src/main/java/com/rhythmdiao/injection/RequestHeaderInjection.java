package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.RequestHeader;
import com.rhythmdiao.handlers.HandlerInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public final class RequestHeaderInjection extends AbstractInjection {
    public void injectField(HandlerInfo handlerInfo, HttpServletRequest request, Map<String, Object> injectedFieldMap) {
        final List<HandlerInfo.FieldWithAnnotation> fieldWithAnnotationList = handlerInfo.getFieldwithAnnotationMap().get(RequestHeader.class);
        for (HandlerInfo.FieldWithAnnotation fieldWithAnnotation : fieldWithAnnotationList) {
            final RequestHeader requestHeader = (RequestHeader) fieldWithAnnotation.getAnnotation();
            String field = requestHeader.value();
            if (Strings.isNullOrEmpty(field)) {
                field = fieldWithAnnotation.getField().getName();
            }
            final String value = request.getHeader(field);
            if (!Strings.isNullOrEmpty(value)) {
                injectedFieldMap.put(field, value);
            }
        }
    }
}
