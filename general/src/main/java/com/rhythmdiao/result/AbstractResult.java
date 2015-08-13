package com.rhythmdiao.result;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractResult implements Serializable, Result {
    private static final long serialVersionUID = 1L;
    private String apiVersion;
    private String context;
    private String id;
    private int statusCode;
    private String statusMsg;
    private Map data;
    private Map error;

    public AbstractResult() {
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

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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
                .add("statusCode", statusCode)
                .add("id", id)
                .add("apiVersion", apiVersion)
                .add("context", context)
                .add("statusMsg", statusMsg)
                .add("data", data)
                .add("error", error)
                .toString();
    }
}
