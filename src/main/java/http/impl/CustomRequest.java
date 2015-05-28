package http.impl;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public class CustomRequest {
    private Map<String, String> customRequestHeaders;
    private Map<String, String> customRequestParameters;

    public Map<String, String> getCustomRequestHeaders() {
        if (customRequestHeaders == null) {
            return null;
        }
        return Collections.unmodifiableMap(customRequestHeaders);
    }

    public String setCustomRequestHeaders(String key, String value) {
        if (customRequestHeaders == null) {
            customRequestHeaders = Maps.newHashMap();
        }
        return customRequestHeaders.put(key, value);
    }

    public Map<String, String> getCustomRequestParameters() {
        if (customRequestParameters == null) {
            return null;
        }
        return Collections.unmodifiableMap(customRequestParameters);
    }

    public String setCustomRequestParameters(String key, String value) {
        if (customRequestParameters == null) {
            customRequestParameters = Maps.newHashMap();
        }
        return customRequestParameters.put(key, value);
    }
}
