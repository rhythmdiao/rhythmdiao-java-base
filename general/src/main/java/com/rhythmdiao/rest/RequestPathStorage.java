package com.rhythmdiao.rest;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.rhythmdiao.handlers.Handler;

public enum RequestPathStorage {
    instance;

    private Table<String, String, Object> pathTable;

    RequestPathStorage() {
        pathTable = HashBasedTable.create();
    }

    public void setPathMap(String method, String uri, Object handler) {
        if (handler instanceof Handler) {
            pathTable.put(method, uri, handler);
        }
    }

    public ImmutableTable<String, String, Object> getPathMap() {
        return ImmutableTable.copyOf(pathTable);
    }

    public static RequestPathStorage getInstance() {
        return instance;
    }
}