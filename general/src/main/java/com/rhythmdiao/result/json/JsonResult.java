package com.rhythmdiao.result.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.utils.ConstResult;

public final class JsonResult extends AbstractResult {
    private static Gson GSON;

    public JsonResult() {
        super();
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

    public JsonResult(boolean excludeFieldsWithoutExposeAnnotation) {
        super();
        GSON = excludeFieldsWithoutExposeAnnotation ? new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create()
                : new GsonBuilder().setPrettyPrinting().create();
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
