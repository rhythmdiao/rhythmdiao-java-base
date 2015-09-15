package com.rhythmdiao.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum HandlerMetaDataList {
    INSTANCE;

    public List<HandlerMetaData> get() {
        return Collections.unmodifiableList(list);
    }

    public void add(HandlerMetaData handlerMetaData) {
        if (!list.contains(handlerMetaData)) {
            list.add(handlerMetaData);
        }
    }

    private List<HandlerMetaData> list;

    HandlerMetaDataList() {
        list = new LinkedList<HandlerMetaData>();
    }
}
