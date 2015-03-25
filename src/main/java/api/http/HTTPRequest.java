package api.http;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.HashMap;

public interface HTTPRequest {
    void addHeader(HttpRequestBase httpRequestBase, HashMap<String, String> headerMap);

    void addParameter(HttpPost httpPost, HashMap<String, String> parameterMap);
}
