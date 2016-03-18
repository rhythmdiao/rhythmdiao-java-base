package com.rhythmdiao.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpTinyClient {
    public static TinyResponse get(String url, HttpProperty property, String encoding, int timeout) {
        HttpURLConnection connection = null;
        TinyResponse response = new TinyResponse();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            setHeaders(connection, property);
            connection.connect();
            response.code = connection.getResponseCode();
            response.content = IOUtil.toString(
                    response.code == 200 ? connection.getInputStream() : connection.getErrorStream()
                    , encoding);
            return response;
        } catch (IOException ignored) {
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return response;
    }

    public static TinyResponse post(String url, HttpProperty property, String encoding, int timeout) {
        HttpURLConnection connection = null;
        TinyResponse response = new TinyResponse();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            setHeaders(connection, property);
            String encodedParams = encodeParams(property, encoding);
            if (encodedParams != null && !encodedParams.equals("")) {
                connection.getOutputStream().write(encodedParams.getBytes());
            }
            connection.connect();
            response.code = connection.getResponseCode();
            response.content = IOUtil.toString(
                    response.code == 200 ? connection.getInputStream() : connection.getErrorStream()
                    , encoding);
        } catch (IOException ignored) {
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return response;
    }

    private static void setHeaders(HttpURLConnection connection, HttpProperty property) {
        if (property.getHeaderMap() != null) {
            for (Map.Entry<String, String> header : property.getHeaderMap().entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }
    }

    private static String encodeParams(HttpProperty property, String encoding) {
        if (property.getParameterMap() != null) {
            StringBuilder builder = new StringBuilder();
            try {
                for (Map.Entry<String, String> parameter : property.getParameterMap().entrySet()) {
                    builder.append(parameter.getKey())
                            .append("=")
                            .append(URLEncoder.encode(parameter.getValue(), encoding))
                            .append("&");
                }
                return builder.toString();
            } catch (UnsupportedEncodingException ignored) {
            }
        }
        return null;
    }

    public static class TinyResponse {
        public int code;
        public String content;
    }
}
