package com.rhythmdiao.result;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Map;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    private String apiVersion;
    @Expose
    private String context;
    @Expose
    private String id;
    @Expose
    private int statusCode;
    @Expose
    private String statusMsg;
    @Expose
    private Map data;
    @Expose
    private Map error;

    public Result() {
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
        return "AbstractResult{" +
                "apiVersion='" + apiVersion + '\'' +
                ", context='" + context + '\'' +
                ", id='" + id + '\'' +
                ", statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}
