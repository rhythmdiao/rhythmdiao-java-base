package utils.http.client;

import api.http.Client;
import constant.Const;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URISyntaxException;
import java.util.HashMap;

public class HttpGetClient extends HttpBaseClient implements Client {
    private static final Logger logger = LoggerFactory.getLogger(HttpGetClient.class);

    @Override
    public String execute(String requestURI, HashMap<String, String> headerMap, HashMap<String, String> parameterMap) {
        try {
            HttpGet httpGet = new HttpGet();
            super.setURI(httpGet, requestURI);
            super.addHeader(httpGet, headerMap);
            return super.getResponse(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Const.EMPTY_STRING;
    }

    @Override
    protected void addParameter(HttpPost httpPost, HashMap<String, String> parameterMap) throws NotImplementedException{
    }
}
