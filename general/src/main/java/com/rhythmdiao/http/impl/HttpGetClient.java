package com.rhythmdiao.http.impl;

import com.rhythmdiao.http.Client;
import org.apache.http.client.methods.HttpGet;

public class HttpGetClient extends HttpBaseClient implements Client {
    public HttpGetClient() {
    }

    public HttpGetClient(String hostAndPort) {
        super(hostAndPort);
    }

    public String execute(String requestURI, HttpRequest httpRequest) {
        HttpGet httpGet = new HttpGet();
        super.setURI(httpGet, requestURI);
        super.addHeader(httpGet, httpRequest);
        return super.sendAndReceive(httpGet);
    }
}
