package com.rhythmdiao.http.impl;

import com.rhythmdiao.http.Client;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

public class HttpGetClient extends HttpBaseClient implements Client {
    private static final Logger LOG = LoggerFactory.getLogger(HttpGetClient.class);

    public HttpGetClient() {
    }

    public HttpGetClient(String hostAndPort) {
        super(hostAndPort);
    }

    public String execute(String requestURI, CustomRequest customRequest) {
        try {
            HttpGet httpGet = new HttpGet();
            super.setURI(httpGet, requestURI);
            super.addCustomHeader(httpGet, customRequest);
            return super.sendAndReceive(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
