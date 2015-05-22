package rest.result.xml;

import com.google.common.base.Charsets;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import constant.Const;
import rest.result.BaseRestResult;
import rest.result.WrappedResponse;

public final class XMLRestResult extends BaseRestResult {
    private static final XStream X_STREAM = new XStream(new DomDriver(Charsets.UTF_8.name()));

    static {
        X_STREAM.autodetectAnnotations(true);
    }

    public XMLRestResult() {
        super();
    }

    @Override
    public String convertToResponse() {
        WrappedResponse wrappedResponse = new WrappedResponse(super.getStatusCode(), super.getResult(), super.getMsg());
        String xml = Const.EMPTY_XML;
        try {
            xml = X_STREAM.toXML(wrappedResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
