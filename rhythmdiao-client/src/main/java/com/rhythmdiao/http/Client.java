package com.rhythmdiao.http;

public interface Client {
    String execute(String requestURI, HttpProperty httpProperty);
}
