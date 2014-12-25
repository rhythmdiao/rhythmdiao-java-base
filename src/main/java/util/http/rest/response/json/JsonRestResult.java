package util.http.rest.response.json;

import com.google.gson.Gson;
import util.constant.Const;
import util.http.rest.response.BaseRestResult;
import util.http.rest.response.WrappedResponse;

public class JsonRestResult extends BaseRestResult {
    @Override
    public String convertToResponse() {
        WrappedResponse wrappedResponse = new WrappedResponse();
        wrappedResponse.setStatusCode(super.getStatusCode());
        wrappedResponse.setResult(super.getResult());
        wrappedResponse.setMsg(super.getMsg());
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
