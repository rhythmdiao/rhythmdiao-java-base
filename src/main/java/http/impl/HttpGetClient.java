package http.impl;

import http.Client;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConstResult;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetClient extends HttpBaseClient implements Client {
    private static final Logger LOG = LoggerFactory.getLogger(HttpGetClient.class);

    public HttpGetClient() {
    }

    public HttpGetClient(String hostAndPort) {
        super(hostAndPort);
    }

    public String execute(String requestURI, CustomRequest customRequest) {
        try {
            HttpGet httpGet = new HttpGet();
            super.setURI(httpGet, requestURI);
            super.addCustomHeader(httpGet, customRequest);
            return super.getResponse(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ConstResult.STRING.getEmpty();
    }
}
