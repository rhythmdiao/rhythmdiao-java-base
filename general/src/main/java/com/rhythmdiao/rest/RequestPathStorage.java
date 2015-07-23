package com.rhythmdiao.rest;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.rhythmdiao.handlers.Handler;

public enum RequestPathStorage {
    INSTANCE;

    private Table<String, String, Object> pathTable;

    RequestPathStorage() {
        pathTable = HashBasedTable.create();
    }

    public void setPathMap(String method, String uri, Object handler) {
        if (Handler.class.isInstance(handler)) {
            pathTable.put(method, uri, handler);
        }
    }

    public ImmutableTable<String, String, Object> getPathMap() {
        return ImmutableTable.copyOf(pathTable);
    }
}
