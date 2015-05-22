package rest.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("response")
public final class WrappedResponse implements Serializable {
    private final int statusCode;
    private final Object result;
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
