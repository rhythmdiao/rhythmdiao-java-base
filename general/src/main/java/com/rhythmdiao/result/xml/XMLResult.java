package com.rhythmdiao.result.xml;

import com.google.common.base.Charsets;
import com.rhythmdiao.result.AbstractResult;
import com.rhythmdiao.utils.ConstResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XStreamAlias("response")
public final class XMLResult extends AbstractResult {
    private static final XStream X_STREAM = new XStream(new DomDriver(Charsets.UTF_8.name()));

    static {
        X_STREAM.autodetectAnnotations(true);
    }

    public XMLResult() {
        super();
    }

    public String convertResult() {
        String xml = ConstResult.XML.getEmpty();
        try {
            xml = X_STREAM.toXML(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
