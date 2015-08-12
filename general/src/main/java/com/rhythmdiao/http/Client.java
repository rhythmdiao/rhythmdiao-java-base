package com.rhythmdiao.http;

import com.rhythmdiao.http.impl.HttpRequest;

public interface Client {
    String execute(String requestURI, HttpRequest httpRequest);
}
