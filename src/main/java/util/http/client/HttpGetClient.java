package util.http.client;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.constant.Const;

import java.net.URISyntaxException;
import java.util.HashMap;

public class HttpGetClient extends HttpBaseClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpGetClient.class);

    public String call(String requestURI, HashMap<String, String> headerMap) {
        try {
            HttpGet httpGet = new HttpGet();
            super.setURI(httpGet, requestURI);
            super.generateHeader(httpGet, headerMap);
            return super.getResponse(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Const.EMPTY_STRING;
    }
}
