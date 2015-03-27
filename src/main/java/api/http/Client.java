package api.http;

import java.util.HashMap;

public interface Client {
    String execute(String requestURI, HashMap<String, String> headerMap, HashMap<String, String> parameterMap);

    String execute(String requestURI, HashMap<String, String> headerMap);
}
