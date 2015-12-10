package com.rhythmdiao.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class HttpBaseClient implements Client{
    private String scheme;
    private String authority;
    private ClientBuilder builder;

    protected HttpBaseClient(String authority) {
        this(null, authority);
    }

    protected HttpBaseClient(String scheme, String authority) {
        this(scheme, authority, new ClientBuilder());
    }

    protected HttpBaseClient(String scheme, String authority, ClientBuilder builder) {
        this.scheme = scheme != null && scheme.equals("https") ? scheme : "http";
        this.authority = authority;
        this.builder = builder;
    }

    protected void setURI(HttpRequestBase httpRequestBase, String path) {
        try {
            httpRequestBase.setURI(new URI(scheme + "://"
                    + ((authority == null || "".equals(authority)) ? path : authority + path)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    protected void addHeader(HttpRequestBase httpRequestBase, HttpRequest httpRequest) {
        if (httpRequest.getHeaderMap() != null) {
            for (Map.Entry<String, String> header : httpRequest.getHeaderMap().entrySet()) {
                httpRequestBase.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    protected void addParameter(HttpPost httpPost, HttpRequest httpRequest) {
        if (httpRequest.getParameterMap() != null) {
            List<NameValuePair> parameters = new LinkedList<NameValuePair>();
            for (Map.Entry<String, String> parameter : httpRequest.getParameterMap().entrySet()) {
                parameters.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
            }
            HttpEntity entity = null;
            try {
                entity = new UrlEncodedFormEntity(parameters, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entity);
        }
    }

    protected String execute(HttpRequestBase request) {
        CloseableHttpClient client = this.builder.getClient();

        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new IOException("Error fetching data from " + request.getURI() + ", and status code is " + response.getStatusLine().getStatusCode());
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) EntityUtils.consume(response.getEntity());
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
