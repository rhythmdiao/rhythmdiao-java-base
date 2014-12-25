package util.http.rest;

import org.eclipse.jetty.server.Request;
import util.http.rest.response.BaseRestResult;

/**
 * Created by mayuxing on 2014/12/19.
 */
public abstract class Handler {
    public abstract BaseRestResult execute(Request request);
}
