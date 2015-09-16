package com.rhythmdiao.http.impl;

import com.rhythmdiao.http.Client;
import org.apache.http.client.methods.HttpGet;

public class HttpGetClient extends HttpBaseClient implements Client {

    public HttpGetClient(String authority) {
        super(authority);
    }

    public HttpGetClient(String scheme, String authority) {
        super(scheme, authority);
    }

    public String execute(String path, HttpRequest httpRequest) {
        HttpGet httpGet = new HttpGet();
        setURI(httpGet, path);
        addHeader(httpGet, httpRequest);
        return sendAndReceive(httpGet);
    }
}
