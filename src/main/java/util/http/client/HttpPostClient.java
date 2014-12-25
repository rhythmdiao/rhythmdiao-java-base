package util.http.client;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

public class HttpPostClient extends HttpBaseClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpPostClient.class);

    public String call(String requestURI, HashMap<String, String> headerMap, HashMap<String, String> parameterMap) {
        try {
            HttpPost httpPost = new HttpPost();
            super.setURI(httpPost, requestURI);
            super.generateHeader(httpPost, headerMap);
            generateParameter(httpPost, parameterMap);
            return super.getResponse(httpPost);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void generateParameter(HttpPost httpPost, HashMap<String, String> parameterMap) {
        if (parameterMap != null) {
            List<NameValuePair> parameterList = Lists.newArrayList();
            for (String key : parameterMap.keySet()) {
                parameterList.add(new BasicNameValuePair(key, parameterMap.get(key)));
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
