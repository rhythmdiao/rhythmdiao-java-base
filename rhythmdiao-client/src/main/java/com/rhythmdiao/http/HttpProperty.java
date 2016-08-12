package com.rhythmdiao.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpProperty {
    private Map<String, String> headerMap;
    private Map<String, String> parameterMap;
    private String body;

    public HttpProperty() {
        headerMap = new HashMap<String, String>();
        parameterMap = new HashMap<String, String>();
        body = "";
    }

    public HttpProperty(Map<String, String> headerMap, Map<String, String> parameterMap, String body) {
        this.headerMap = headerMap;
        this.parameterMap = parameterMap;
        this.body = body;
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

    public HttpProperty setBody(String body) {
        this.body = body;
        return this;
    }

    public String getBody() {
        return body;
    }
}
