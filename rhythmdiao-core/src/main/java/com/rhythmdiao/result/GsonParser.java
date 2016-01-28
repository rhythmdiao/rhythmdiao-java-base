package com.rhythmdiao.result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonParser implements Parser {
    private Result result;
    private GsonBuilder builder;

    private Gson gson() {
        return builder.create();
    }

    public GsonParser(Result result, GsonBuilder builder) {
        this.result = result;
        this.builder = builder;
    }

    public GsonParser(Result result) {
        this(result, new GsonBuilder()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization());
    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public String toString() {
        return gson().toJson(result);
    }
}
