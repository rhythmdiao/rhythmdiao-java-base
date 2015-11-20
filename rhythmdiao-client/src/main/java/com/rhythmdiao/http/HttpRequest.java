package com.rhythmdiao.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private Map<String, String> headerMap;
    private Map<String, String> parameterMap;

    public HttpRequest() {
        headerMap = new HashMap<String, String>();
        parameterMap = new HashMap<String, String>();
    }

    public HttpRequest(Map<String, String> headerMap, Map<String, String> parameterMap) {
        this.headerMap = headerMap;
        this.parameterMap = parameterMap;
    }

    public Map<String, String> getHeaderMap() {
        return Collections.unmodifiableMap(headerMap);
    }

    public void setHeader(String key, String value) {
        headerMap.put(key, value);
    }

    public Map<String, String> getParameterMap() {
        return Collections.unmodifiableMap(parameterMap);
    }

    public void setParameter(String key, String value) {
        parameterMap.put(key, value);
    }
}
