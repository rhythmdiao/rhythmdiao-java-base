package com.rhythmdiao.result;

import com.thoughtworks.xstream.XStream;

public class XmlParser implements Parser {
    private Result result;
    private XStream xStream;

    public XmlParser(Result result) {
        this.result = result;
        this.xStream = new XStream();
        this.xStream.autodetectAnnotations(true);
        this.xStream.setMode(XStream.NO_REFERENCES);
    }

    public Result getResult() {
        return result;
    }

    public XmlParser(Result result, XStream xStream) {
        this.result = result;
        this.xStream = xStream;
    }

    @Override
    public String getContentType() {
        return "application/xml";
    }

    @Override
    public String toString() {
        return this.xStream.toXML(result);
    }
}
