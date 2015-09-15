package com.rhythmdiao.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum TypeConverter {
    ;
    private static final Map<Class, Type> map = new HashMap<Class, Type>(11);

    static {
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
    }


    public static Object convert(String s, Class cls) {
        Type type = map.get(cls);
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
                //TODO
            default:
                break;
        }
        return s;
    }

    private enum Type {
        INT, DOUBLE, FLOAT, LONG, BOOLEAN, DATE
    }
}
