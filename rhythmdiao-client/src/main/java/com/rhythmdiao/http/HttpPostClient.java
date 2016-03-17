package com.rhythmdiao.http;

import org.apache.http.client.methods.HttpPost;

public class HttpPostClient extends HttpBaseClient {

    public HttpPostClient(String context) {
        super(context);
    }

    public HttpPostClient(String scheme, String context) {
        super(scheme, context);
    }

    public String execute(String path, HttpProperty httpProperty) {
        HttpPost httpPost = new HttpPost();
        setURI(httpPost, path);
        addHeader(httpPost, httpProperty);
        addParameter(httpPost, httpProperty);
        return execute(httpPost);
    }
}
