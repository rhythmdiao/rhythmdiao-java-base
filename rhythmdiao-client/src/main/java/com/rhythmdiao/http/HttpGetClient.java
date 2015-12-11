package com.rhythmdiao.http;

import org.apache.http.client.methods.HttpGet;

public class HttpGetClient extends HttpBaseClient {

    public HttpGetClient(String context) {
        super(context);
    }

    public HttpGetClient(String scheme, String context) {
        super(scheme, context);
    }

    public String execute(String path, HttpRequest httpRequest) {
        HttpGet httpGet = new HttpGet();
        setURI(httpGet, path);
        addHeader(httpGet, httpRequest);
        return execute(httpGet);
    }
}
