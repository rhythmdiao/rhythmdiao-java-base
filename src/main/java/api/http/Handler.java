package api.http;

import org.eclipse.jetty.server.Request;
import rest.result.BaseRestResult;

public interface Handler {
    BaseRestResult execute(Request request);
}
