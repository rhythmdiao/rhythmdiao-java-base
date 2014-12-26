package utils.http.rest.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("response")
public final class WrappedResponse implements Serializable {
    @XStreamAlias("statusCode")
    private final int statusCode;
    @XStreamAlias("result")
    private final Object result;
    @XStreamAlias("msg")
    private final String msg;

    public WrappedResponse(final int statusCode, final Object result, final String msg) {
        this.statusCode = statusCode;
        this.result = result;
        this.msg = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }
}
