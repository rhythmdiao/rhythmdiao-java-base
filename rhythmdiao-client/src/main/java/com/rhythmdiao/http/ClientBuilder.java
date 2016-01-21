package com.rhythmdiao.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public enum ClientBuilder {
    INSTANCE;

    private final HttpClientBuilder builder;

    ClientBuilder() {
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(5000).setTcpNoDelay(true).build();
        ConnectionManager.INSTANCE.getConnectionManager().setDefaultMaxPerRoute(10);
        ConnectionManager.INSTANCE.getConnectionManager().setMaxTotal(200);
        ConnectionManager.INSTANCE.getConnectionManager().setDefaultSocketConfig(socketConfig);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
        builder = HttpClients
                .custom()
                .setConnectionManager(ConnectionManager.INSTANCE.getConnectionManager())
                .setDefaultRequestConfig(requestConfig)
                .setDefaultSocketConfig(socketConfig)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
    }

    public CloseableHttpClient getClient() {
        return builder.build();
    }
}
