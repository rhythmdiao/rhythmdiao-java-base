import base.BaseTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.rhythmdiao.http.HttpProperty;
import com.rhythmdiao.http.HttpTinyClient;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HttpTinyClientTest extends BaseTest {
    @Test
    public void testTinyGet() {
        HttpProperty httpProperty = new HttpProperty();
        httpProperty
                .setHeader("field1", "886")
                .setParameter("field2", "rhythmdiao")
                .setParameter("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LinkedTreeMap object = (LinkedTreeMap) gson.fromJson(HttpTinyClient.get("http://localhost:8081/test", httpProperty).content, Object.class);
        LOG.info(object.toString());
    }
}
