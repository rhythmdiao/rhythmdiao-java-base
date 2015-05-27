package http.impl;

import constant.Const;
import http.Client;
import org.apache.commons.lang.NotImplementedException;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.HashMap;

public class HttpGetClient extends HttpBaseClient implements Client {
    private static final Logger LOG = LoggerFactory.getLogger(HttpGetClient.class);

    public HttpGetClient() {
    }

    public HttpGetClient(String hostAndPort) {
        super(hostAndPort);
    }

    public String execute(String requestURI, HashMap<String, String> headerMap, HashMap<String, String> parameterMap) {
        throw new NotImplementedException();
    }

    public String execute(String requestURI, HashMap<String, String> headerMap) {
        try {
            HttpGet httpGet = new HttpGet();
            super.setURI(httpGet, requestURI);
            super.addCustomHeader(httpGet, headerMap);
            return super.getResponse(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Const.EMPTY_STRING;
    }
}
