package utils.tool;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Basic {
    public static String toString(Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
    }
}
