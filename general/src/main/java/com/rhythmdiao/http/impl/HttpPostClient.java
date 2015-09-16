package com.rhythmdiao.http.impl;

import com.rhythmdiao.http.Client;
import org.apache.http.client.methods.HttpPost;

public class HttpPostClient extends HttpBaseClient implements Client {

    public HttpPostClient(String authority) {
        super(authority);
    }

    public HttpPostClient(String scheme, String authority) {
        super(scheme, authority);
    }

    public String execute(String path, HttpRequest httpRequest) {
        HttpPost httpPost = new HttpPost();
        setURI(httpPost, path);
        addHeader(httpPost, httpRequest);
        addParameter(httpPost, httpRequest);
        return sendAndReceive(httpPost);
    }
}
