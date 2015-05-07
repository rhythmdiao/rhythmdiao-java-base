package rest.result;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "response")
public final class WrappedResponse implements Serializable {
    @XmlElement
    private final int statusCode;
    @XmlElement
    private final Object result;
    @XmlElement
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
