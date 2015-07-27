package com.rhythmdiao.rest.result;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Map;

public abstract class BaseRestResult implements Serializable,Result {
    private static final long serialVersionUID = 1L;
    private String apiVersion;
    private String context;
    private String id;
    private Map data;
    private Map error;

    public BaseRestResult() {
        this.apiVersion = "1.0";
        this.context = "default";
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public Map getError() {
        return error;
    }

    public void setError(Map error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("apiVersion", apiVersion)
                .add("context", context)
                .add("data", data)
                .add("error", error)
                .toString();
    }
}
