package util.http.rest.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("response")
public class WrappedResponse implements Serializable {
    @XStreamAlias("statusCode")
    private int statusCode;
    @XStreamAlias("result")
    private Object result;
    @XStreamAlias("msg")
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

}
