package rest.result.json;

import com.google.gson.Gson;
import constant.Const;
import rest.result.BaseRestResult;
import rest.result.WrappedResponse;

public final class JsonRestResult extends BaseRestResult {
    @Override
    public String convertToResponse() {
        WrappedResponse wrappedResponse = new WrappedResponse(super.getStatusCode(),super.getResult(),super.getMsg());
        String json = Const.EMPTY_JSON;
        try {
            Gson gson = new Gson();
            json = gson.toJson(wrappedResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
