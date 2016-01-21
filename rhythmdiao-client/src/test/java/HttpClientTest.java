import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.rhythmdiao.http.HttpGetClient;
import com.rhythmdiao.http.HttpPostClient;
import com.rhythmdiao.http.HttpRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class HttpClientTest {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientTest.class);

    @Test
    public void testHttpGet() {
        HttpGetClient httpGetClient = new HttpGetClient("http", "localhost:8081");
        HttpRequest httpRequest = new HttpRequest();
        httpRequest
                .setHeader("field1", "886")
                .setParameter("field2", "rhythmdiao")
                .setParameter("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
        String response = httpGetClient.execute("/test", httpRequest);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map jsonObject = (LinkedTreeMap) gson.fromJson(response, Object.class);
        LOG.info(jsonObject.toString());
    }

    @Test
    public void testHttpPost() {
        HttpPostClient httpPostClient = new HttpPostClient("http", "localhost:8081");
        HttpRequest httpRequest = new HttpRequest();
        httpRequest
                .setHeader("field1", "886")
                .setParameter("field2", "rhythmdiao")
                .setParameter("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
        String response = httpPostClient.execute("/test", httpRequest);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map jsonObject = (LinkedTreeMap) gson.fromJson(response, Object.class);
        LOG.info(jsonObject.toString());
    }
}
