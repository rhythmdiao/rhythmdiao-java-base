package util.tool;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by mayuxing on 2014/12/18.
 */
public class Basic {
    public static String toString(Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
    }
}
