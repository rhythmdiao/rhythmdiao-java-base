package com.rhythmdiao.handler;

import com.rhythmdiao.result.Parser;

public interface Handler {
    Parser execute();

    void describe(String... keys);
}
