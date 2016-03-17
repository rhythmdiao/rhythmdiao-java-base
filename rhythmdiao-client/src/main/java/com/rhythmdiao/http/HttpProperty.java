package com.rhythmdiao.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpProperty {
    private Map<String, String> headerMap;
    private Map<String, String> parameterMap;

    public HttpProperty() {
        headerMap = new HashMap<String, String>();
        parameterMap = new HashMap<String, String>();
    }

    public HttpProperty(Map<String, String> headerMap, Map<String, String> parameterMap) {
        this.headerMap = headerMap;
        this.parameterMap = parameterMap;
    }

    public Map<String, String> getHeaderMap() {
        return Collections.unmodifiableMap(headerMap);
    }

    public HttpProperty setHeader(String key, String value) {
        headerMap.put(key, value);
        return this;
    }

    public Map<String, String> getParameterMap() {
        return Collections.unmodifiableMap(parameterMap);
    }

    public HttpProperty setParameter(String key, String value) {
        parameterMap.put(key, value);
        return this;
    }
}
