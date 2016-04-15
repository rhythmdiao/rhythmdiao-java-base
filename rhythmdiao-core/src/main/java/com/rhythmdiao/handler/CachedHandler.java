package com.rhythmdiao.handler;

import com.rhythmdiao.cache.Cache;

public abstract class CachedHandler extends BaseHandler implements Cache {
    @Override
    public String getKey() {
        return null;
    }
}
