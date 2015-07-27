package com.rhythmdiao.rest.result.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rhythmdiao.rest.result.BaseRestResult;
import com.rhythmdiao.utils.ConstResult;

public final class JsonRestResult extends BaseRestResult {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public JsonRestResult() {
        super();
    }

    public String convertResult() {
        String json = ConstResult.JSON.getEmpty();
        try {
            json = GSON.toJson(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
