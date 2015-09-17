package com.rhythmdiao.http.impl;

import com.rhythmdiao.http.HttpMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpBaseClient implements HttpMessage {
    private String scheme = "http";
    private String authority;
    private static SSLContext sslContext;

    static {
        //Unsafe, trust everything
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

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
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(5000).build();
        if (scheme.equals("https")) {
            httpClientBuilder.setSslcontext(sslContext);
        }
        CloseableHttpClient closeableHttpClient = httpClientBuilder.setDefaultSocketConfig(socketConfig).build();

        HttpResponse response;
        try {
            response = closeableHttpClient.execute(request);
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
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
