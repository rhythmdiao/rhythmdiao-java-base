package utils.http.rest.response.json;

import com.google.gson.Gson;
import constant.Const;
import utils.http.rest.response.BaseRestResult;
import utils.http.rest.response.WrappedResponse;

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
