package com.rhythmdiao.http.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

public class CustomRequest {
    private Map<String, String> customHeaderMap;
    private Map<String, String> parameterMap;

    public CustomRequest() {
        customHeaderMap = Maps.newHashMap();
        parameterMap = Maps.newHashMap();
    }

    public CustomRequest(Map<String, String> customHeaderMap, Map<String, String> parameterMap) {
        this.customHeaderMap = customHeaderMap;
        this.parameterMap = parameterMap;
    }

    public ImmutableMap<String, String> getCustomHeaderMap() {
        return ImmutableMap.copyOf(customHeaderMap);
    }

    public String setCustomRequestHeaders(String key, String value) {
        return customHeaderMap.put(key, value);
    }

    public ImmutableMap<String, String> getParameterMap() {
        return ImmutableMap.copyOf(parameterMap);
    }

    public String setCustomRequestParameters(String key, String value) {
        return parameterMap.put(key, value);
    }
}
