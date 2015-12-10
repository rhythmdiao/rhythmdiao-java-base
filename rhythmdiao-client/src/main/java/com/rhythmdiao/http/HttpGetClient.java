package com.rhythmdiao.http;

import org.apache.http.client.methods.HttpGet;

public class HttpGetClient extends HttpBaseClient {

    public HttpGetClient(String authority) {
        super(authority);
    }

    public HttpGetClient(String scheme, String authority) {
        super(scheme, authority);
    }

    public HttpGetClient(String scheme, String authority, ClientBuilder builder) {
        super(scheme, authority, builder);
    }

    public String execute(String path, HttpRequest httpRequest) {
        HttpGet httpGet = new HttpGet();
        setURI(httpGet, path);
        addHeader(httpGet, httpRequest);
        return execute(httpGet);
    }
}
