package api.http;

import com.sun.istack.internal.Nullable;

import java.util.HashMap;

public interface Client {
    String execute(String requestURI, @Nullable HashMap<String, String> headerMap, @Nullable HashMap<String, String> parameterMap);
}
