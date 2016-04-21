package com.rhythmdiao.result;

public interface Parser {
    String getContentType();

    String parse();

    Result getResult();
}
