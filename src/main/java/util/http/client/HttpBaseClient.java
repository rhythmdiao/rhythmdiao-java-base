package util.http.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static com.google.common.base.Strings.isNullOrEmpty;

public abstract class HttpBaseClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpBaseClient.class);

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    protected void setURI(HttpRequestBase httpRequestBase, String requestURI) throws URISyntaxException {
        httpRequestBase.setURI(new URI(isNullOrEmpty(host) ? requestURI : host + requestURI));
    }

    protected void generateHeader(HttpRequestBase httpRequestBase, HashMap<String, String> headerMap) {
        if (headerMap != null) {
            for (String key : headerMap.keySet()) {
                httpRequestBase.addHeader(key, headerMap.get(key));
            }
        }
    }

    protected String getResponse(HttpRequestBase httpRequestBase) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            //DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpResponse response = closeableHttpClient.execute(httpRequestBase);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
