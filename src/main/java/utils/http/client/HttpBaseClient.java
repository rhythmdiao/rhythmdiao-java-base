package utils.http.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
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
import java.util.Map;

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

    protected void addHeader(HttpRequestBase httpRequestBase, HashMap<String, String> headerMap) {
        if (headerMap != null) {
            for (Map.Entry<String, String> header : headerMap.entrySet()) {
                httpRequestBase.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    protected abstract void addParameter(HttpPost httpPost, HashMap<String, String> parameterMap);

    protected String getResponse(HttpRequestBase httpRequestBase) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            HttpResponse response = closeableHttpClient.execute(httpRequestBase);
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
