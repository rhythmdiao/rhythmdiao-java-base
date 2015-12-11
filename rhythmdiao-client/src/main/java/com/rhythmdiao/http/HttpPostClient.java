package com.rhythmdiao.http;

import org.apache.http.client.methods.HttpPost;

public class HttpPostClient extends HttpBaseClient {

    public HttpPostClient(String context) {
        super(context);
    }

    public HttpPostClient(String scheme, String context) {
        super(scheme, context);
    }

    public String execute(String path, HttpRequest httpRequest) {
        HttpPost httpPost = new HttpPost();
        setURI(httpPost, path);
        addHeader(httpPost, httpRequest);
        addParameter(httpPost, httpRequest);
        return execute(httpPost);
    }
}
