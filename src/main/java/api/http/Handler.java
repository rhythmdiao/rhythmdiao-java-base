package api.http;

import org.eclipse.jetty.server.Request;
import rest.result.BaseRestResult;

public interface Handler {
    public abstract BaseRestResult execute(Request request);
}
