package http;

import http.impl.CustomRequest;

public interface Client {
    String execute(String requestURI, CustomRequest customRequest);
}
