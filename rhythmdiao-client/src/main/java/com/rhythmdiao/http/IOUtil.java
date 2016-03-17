package com.rhythmdiao.http;

import java.io.*;

public class IOUtil {
    public static String toString(InputStream in, String encoding) {
        try {
            InputStreamReader reader = new InputStreamReader(in, encoding);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    builder.append(line);
                } else {
                    break;
                }
            }
            return builder.toString();
        } catch (UnsupportedEncodingException ignored) {
        } catch (IOException ignored) {
        }
        return null;
    }

}
