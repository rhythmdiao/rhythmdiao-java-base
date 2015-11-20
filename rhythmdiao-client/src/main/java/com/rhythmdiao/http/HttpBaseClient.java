package com.rhythmdiao.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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

public class HttpBaseClient implements HttpMessage {
    private String scheme = "http";
    private String authority;

    public HttpBaseClient(String authority) {
        this.authority = authority;
    }

    public HttpBaseClient(String scheme, String authority) {
        this.scheme = scheme;
        this.authority = authority;
    }

    protected void setURI(HttpRequestBase httpRequestBase, String path) {
        try {
            httpRequestBase.setURI(new URI(("https".equals(scheme) ? "https://" : "http://")
                    + ((authority == null || "".equals(authority)) ? path : authority + path)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void addHeader(HttpRequestBase httpRequestBase, HttpRequest httpRequest) {
        if (httpRequest.getHeaderMap() != null) {
            for (Map.Entry<String, String> header : httpRequest.getHeaderMap().entrySet()) {
                httpRequestBase.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    public void addParameter(HttpPost httpPost, HttpRequest httpRequest) {
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

    public String sendAndReceive(HttpRequestBase request) {
        CloseableHttpClient client = new HttpClientCustomBuilder().getClient();

        HttpResponse response;
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
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
