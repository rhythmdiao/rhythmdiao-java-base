package rest.result.xml;

import com.google.common.base.Charsets;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import constant.Const;
import rest.result.BaseRestResult;
import rest.result.WrappedResponse;

public final class XMLRestResult extends BaseRestResult {
    @Override
    public String convertToResponse() {
        WrappedResponse wrappedResponse = new WrappedResponse(super.getStatusCode(),super.getResult(),super.getMsg());
        String xml = Const.EMPTY_XML;
        try {
            XStream xStream = new XStream(new DomDriver(Charsets.UTF_8.name()));
            xStream.autodetectAnnotations(true);
            xml = xStream.toXML(wrappedResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
