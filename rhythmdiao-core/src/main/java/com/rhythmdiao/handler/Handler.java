package com.rhythmdiao.handler;

import com.rhythmdiao.result.AbstractResult;
import org.eclipse.jetty.server.Request;

public interface Handler {
    AbstractResult execute(Request request);

    AbstractResult execute();
}
