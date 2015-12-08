package com.rhythmdiao.result.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.utils.ConstResult;

public final class JsonResult extends AbstractResult {
    private static Gson GSON;

    public JsonResult() {
        this(false);
    }

    public JsonResult(boolean excludeFieldsWithoutExposeAnnotation) {
        super();
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization();
        if (excludeFieldsWithoutExposeAnnotation)
            builder.excludeFieldsWithoutExposeAnnotation();
        GSON = builder.create();
    }

    public JsonResult(GsonBuilder builder) {
        GSON = builder.create();
    }

    public String to() {
        String json = ConstResult.JSON.getEmpty();
        try {
            json = GSON.toJson(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
