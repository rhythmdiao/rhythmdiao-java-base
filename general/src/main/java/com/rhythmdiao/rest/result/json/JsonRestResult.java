package com.rhythmdiao.rest.result.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rhythmdiao.rest.result.WrappedResponse;
import com.rhythmdiao.utils.ConstResult;
import com.rhythmdiao.rest.result.BaseRestResult;

public final class JsonRestResult extends BaseRestResult {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public JsonRestResult() {
        super();
    }

    @Override
    public String convertToResponse() {
        WrappedResponse wrappedResponse = new WrappedResponse(super.getStatusCode(), super.getResult(), super.getMsg());
        String json = ConstResult.JSON.getEmpty();
        try {
            json = GSON.toJson(wrappedResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
