package com.rhythmdiao.http;

import com.rhythmdiao.http.impl.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;

public interface HttpMessage {
    void addHeader(HttpRequestBase httpRequestBase, HttpRequest httpRequest);

    void addParameter(HttpPost httpPost, HttpRequest httpRequest);

    String sendAndReceive(HttpRequestBase httpRequestBase) throws IOException;
}
