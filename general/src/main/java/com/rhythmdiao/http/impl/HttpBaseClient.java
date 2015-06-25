package com.rhythmdiao.http.impl;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Closeables;
import com.google.common.net.HostAndPort;
import com.rhythmdiao.http.HttpMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

public class HttpBaseClient implements HttpMessage {
    private static final String URI_HTTP_PREFIX = "http://";
    private static final Logger LOG = LoggerFactory.getLogger(HttpBaseClient.class);

    private String hostAndPort;

    public HttpBaseClient(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    public HttpBaseClient() {
    }

    public String getHostAndPort() {
        return hostAndPort;
    }

    public void setHostAndPort(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    public String getHost() {
        return HostAndPort.fromString(hostAndPort).getHostText();
    }

    public int getPort() {
        return HostAndPort.fromString(hostAndPort).getPort();
    }

    protected void setURI(HttpRequestBase httpRequestBase, String requestURI) throws URISyntaxException {
        httpRequestBase.setURI(new URI(URI_HTTP_PREFIX + (isNullOrEmpty(hostAndPort) ? requestURI : hostAndPort + requestURI)));
    }

    public void addCustomHeader(HttpRequestBase httpRequestBase, CustomRequest customRequest) {
        if (customRequest.getCustomHeaderMap() != null) {
            for (Map.Entry<String, String> header : customRequest.getCustomHeaderMap().entrySet()) {
                httpRequestBase.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    public void addParameter(HttpPost httpPost, CustomRequest customRequest) {
        if (customRequest.getParameterMap() != null) {
            List<NameValuePair> parameterList = Lists.newArrayList();
            for (Map.Entry<String, String> parameter : customRequest.getParameterMap().entrySet()) {
                parameterList.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
            }
            HttpEntity entity = null;
            try {
                entity = new UrlEncodedFormEntity(parameterList, Charsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entity);
        }
    }

    public String sendAndReceive(HttpRequestBase request) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpResponse response = closeableHttpClient.execute(request);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new IOException("Error fetching data from " + request.getURI());
        }

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            return EntityUtils.toString(entity, Charsets.UTF_8.name());
        }

        Closeables.close(closeableHttpClient, true);
        return null;
    }
}
