package com.rhythmdiao.http;

import com.rhythmdiao.http.impl.CustomRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;

public interface HttpMessage {
    void addCustomHeader(HttpRequestBase httpRequestBase, CustomRequest customRequest);

    void addParameter(HttpPost httpPost, CustomRequest customRequest);

    String sendAndReceive(HttpRequestBase httpRequestBase) throws IOException;
}
