package com.rhythmdiao.http.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomRequest {
    private Map<String, String> customHeaderMap;
    private Map<String, String> parameterMap;

    public CustomRequest() {
        customHeaderMap = new HashMap<String, String>();
        parameterMap = new HashMap<String, String>();
    }

    public CustomRequest(Map<String, String> customHeaderMap, Map<String, String> parameterMap) {
        this.customHeaderMap = customHeaderMap;
        this.parameterMap = parameterMap;
    }

    public Map<String, String> getCustomHeaderMap() {
        return Collections.unmodifiableMap(customHeaderMap);
    }

    public String setCustomRequestHeaders(String key, String value) {
        return customHeaderMap.put(key, value);
    }

    public Map<String, String> getParameterMap() {
        return Collections.unmodifiableMap(parameterMap);
    }

    public String setCustomRequestParameters(String key, String value) {
        return parameterMap.put(key, value);
    }
}
