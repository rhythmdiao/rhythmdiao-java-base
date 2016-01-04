package com.rhythmdiao.handler;

import com.google.common.collect.*;

import java.util.Set;

public enum HandlerPath {
    INSTANCE;

    private Table<String, String, Register> pathTable;
    private Set<String> ignorePaths;

    HandlerPath() {
        pathTable = HashBasedTable.create();
        ignorePaths = Sets.newHashSet();
    }

    protected void setPathMap(String method, String uri, BaseHandler handler) {
        Register register = new Register(handler);
        pathTable.put(method, uri, register);
    }

    protected void setIgnorePath(String target) {
        ignorePaths.add(target);
    }

    protected Register getRegisteredHandler(String method, String target) {
        return getPath().row(method).get(target);
    }

    protected ImmutableTable<String, String, Register> getPath() {
        return ImmutableTable.copyOf(pathTable);
    }
}
