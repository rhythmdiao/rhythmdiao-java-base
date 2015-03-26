package http;

import api.http.HTTPRequest;
import api.http.HTTPResponse;
import com.google.common.base.Charsets;
import com.google.common.net.HostAndPort;
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

public abstract class HttpBaseClient implements HTTPRequest, HTTPResponse {

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
        httpRequestBase.setURI(new URI("http://" + (isNullOrEmpty(hostAndPort) ? requestURI : hostAndPort + requestURI)));
    }

    public void addHeader(HttpRequestBase httpRequestBase, HashMap<String, String> headerMap) {
        if (headerMap != null) {
            for (Map.Entry<String, String> header : headerMap.entrySet()) {
                httpRequestBase.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    public void addParameter(HttpPost httpPost, HashMap<String, String> parameterMap) {
    }

    public String getResponse(HttpRequestBase httpRequestBase) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            HttpResponse response = closeableHttpClient.execute(httpRequestBase);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, Charsets.UTF_8.name());
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
