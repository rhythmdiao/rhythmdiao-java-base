package com.rhythmdiao.http;

import org.apache.http.client.methods.HttpPost;

public class HttpPostClient extends HttpBaseClient {

    public HttpPostClient(String authority) {
        super(authority);
    }

    public HttpPostClient(String scheme, String authority) {
        super(scheme, authority);
    }

    public HttpPostClient(String scheme, String authority, ClientBuilder builder) {
        super(scheme, authority, builder);
    }

    public String execute(String path, HttpRequest httpRequest) {
        HttpPost httpPost = new HttpPost();
        setURI(httpPost, path);
        addHeader(httpPost, httpRequest);
        addParameter(httpPost, httpRequest);
        return execute(httpPost);
    }
}
