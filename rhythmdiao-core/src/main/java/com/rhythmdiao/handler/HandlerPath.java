package com.rhythmdiao.handler;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

public enum HandlerPath {
    INSTANCE;

    private Table<String, String, Register> pathTable;

    HandlerPath() {
        pathTable = HashBasedTable.create();
    }

    public void setPathMap(String method, String uri, BaseHandler handler) {
        Register register = new Register(handler);
        pathTable.put(method, uri, register);
    }

    public ImmutableTable<String, String, Register> getPath() {
        return ImmutableTable.copyOf(pathTable);
    }
}
