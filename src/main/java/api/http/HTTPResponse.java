package api.http;

import org.apache.http.client.methods.HttpRequestBase;

public interface HTTPResponse {
    String getResponse(HttpRequestBase httpRequestBase);
}
