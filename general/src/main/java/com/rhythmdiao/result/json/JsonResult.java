package com.rhythmdiao.result.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.utils.ConstResult;

public final class JsonResult extends AbstractResult {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public JsonResult() {
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
