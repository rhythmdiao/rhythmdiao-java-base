package com.rhythmdiao.http;

import com.rhythmdiao.http.impl.CustomRequest;

public interface Client {
    String execute(String requestURI, CustomRequest customRequest);
}
