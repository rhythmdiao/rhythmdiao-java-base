package com.rhythmdiao.http.impl;

import com.rhythmdiao.http.Client;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpPostClient extends HttpBaseClient implements Client {
    private static final Logger LOG = LoggerFactory.getLogger(HttpPostClient.class);

    public HttpPostClient() {
    }

    public HttpPostClient(String hostAndPort) {
        super(hostAndPort);
    }

    public String execute(String requestURI, CustomRequest customRequest) {
        try {
            HttpPost httpPost = new HttpPost();
            super.setURI(httpPost, requestURI);
            super.addCustomHeader(httpPost, customRequest);
            super.addParameter(httpPost, customRequest);
            return super.sendAndReceive(httpPost);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
