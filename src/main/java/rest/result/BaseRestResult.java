package rest.result;

import java.io.Serializable;
import java.util.Map;

public abstract class BaseRestResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private int statusCode;
    private Map result;
    private String msg;

    public Map getResult() {
        return result;
    }

    public void setResult(Map result) {
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
