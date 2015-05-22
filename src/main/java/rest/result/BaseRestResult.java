package rest.result;

import java.io.Serializable;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public abstract class BaseRestResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private int statusCode;
    private Map result;
    private String msg;

    public BaseRestResult() {
        statusCode = CustomStatusCode.UNKNOWN_HTTP_SOURCE.getStatusCode();
        msg = "UNKNOWN HTTP SOURCE";
        result = newHashMap();
    }

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
