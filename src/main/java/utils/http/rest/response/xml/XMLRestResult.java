package utils.http.rest.response.xml;

import constant.Const;
import utils.http.rest.response.BaseRestResult;
import utils.http.rest.response.WrappedResponse;
import utils.tool.CommonUtil;

public final class XMLRestResult extends BaseRestResult {
    @Override
    public String convertToResponse() {
        WrappedResponse wrappedResponse = new WrappedResponse(super.getStatusCode(),super.getResult(),super.getMsg());
        String xml = Const.EMPTY_XML;
        try {
            xml = CommonUtil.objectToXml(wrappedResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
