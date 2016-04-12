package com.rhythmdiao;

import com.rhythmdiao.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class TypeConverter {
    private static final Map<Class, Type> map = new HashMap<Class, Type>(13);

    private static final Logger LOG = LoggerFactory.getLogger(TypeConverter.class);

    static {
        map.put(String.class, Type.STRING);
        map.put(int.class, Type.INT);
        map.put(Integer.class, Type.INT);
        map.put(double.class, Type.DOUBLE);
        map.put(Double.class, Type.DOUBLE);
        map.put(float.class, Type.FLOAT);
        map.put(Float.class, Type.FLOAT);
        map.put(long.class, Type.LONG);
        map.put(Long.class, Type.LONG);
        map.put(boolean.class, Type.BOOLEAN);
        map.put(Boolean.class, Type.BOOLEAN);
        map.put(Date.class, Type.DATE);
        map.put(Calendar.class, Type.CALENDAR);
    }


    public static Object convert(String s, Class targetType) {
        return convert(s, targetType, null);
    }

    public static Object convert(String s, Class targetType, String format) {
        try {
            Type type = map.get(targetType);
            switch (type) {
                case INT:
                    return Integer.parseInt(s);
                case DOUBLE:
                    return Double.parseDouble(s);
                case FLOAT:
                    return Float.parseFloat(s);
                case LONG:
                    return Long.parseLong(s);
                case BOOLEAN:
                    return Boolean.parseBoolean(s);
                case DATE:
                    return new SimpleDateFormat(format).parse(s);
                case CALENDAR:
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new SimpleDateFormat(format).parse(s));
                    return calendar;
                case STRING:
                default:
                    return s;
            }
        } catch (ParseException e) {
            LogUtil.error(LOG, "Failed to convert {}, and the message: {}", s, e.getMessage());
            return null;
        }
    }

    private enum Type {
        STRING, INT, DOUBLE, FLOAT, LONG, BOOLEAN, DATE, CALENDAR
    }
}
