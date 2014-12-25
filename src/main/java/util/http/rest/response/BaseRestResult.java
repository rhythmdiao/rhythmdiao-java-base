package util.http.rest.response;

import java.io.Serializable;

public abstract class BaseRestResult<E extends Serializable> implements Serializable {
    private int statusCode;
    private Object result;
    private String msg;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public abstract String convertToResponse();
}
