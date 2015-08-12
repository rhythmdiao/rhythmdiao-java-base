package com.rhythmdiao.http.impl;

import com.rhythmdiao.http.Client;
import org.apache.http.client.methods.HttpPost;

public class HttpPostClient extends HttpBaseClient implements Client {
    public HttpPostClient() {
    }

    public HttpPostClient(String hostAndPort) {
        super(hostAndPort);
    }

    public String execute(String requestURI, HttpRequest httpRequest) {
        HttpPost httpPost = new HttpPost();
        super.setURI(httpPost, requestURI);
        super.addHeader(httpPost, httpRequest);
        super.addParameter(httpPost, httpRequest);
        return super.sendAndReceive(httpPost);
    }
}
