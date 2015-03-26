package http;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import api.http.Client;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpPostClient extends HttpBaseClient implements Client {
    private static final Logger LOG = LoggerFactory.getLogger(HttpPostClient.class);

    @Override
    public String execute(String requestURI, HashMap<String, String> headerMap, HashMap<String, String> parameterMap) {
        try {
            HttpPost httpPost = new HttpPost();
            super.setURI(httpPost, requestURI);
            super.addHeader(httpPost, headerMap);
            addParameter(httpPost, parameterMap);
            return super.getResponse(httpPost);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addParameter(HttpPost httpPost, HashMap<String, String> parameterMap) {
        if (parameterMap != null) {
            List<NameValuePair> parameterList = Lists.newArrayList();
            for (Map.Entry<String, String> parameter : parameterMap.entrySet()) {
                parameterList.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
            }
            HttpEntity entity = null;
            try {
                entity = new UrlEncodedFormEntity(parameterList, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entity);
        }
    }
}
