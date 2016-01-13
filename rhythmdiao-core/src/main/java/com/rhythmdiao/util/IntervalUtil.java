package com.rhythmdiao.util;

import java.util.ArrayList;
import java.util.List;

public class IntervalUtil {
    private long start = -1;

    private List<Long> marks;

    public IntervalUtil() {
        marks = new ArrayList<Long>();
    }

    public IntervalUtil start() {
        reset();
        start = currentTime();
        marks.add(start);
        return this;
    }

    public void mark() {
        marks.add(currentTime());
    }

    public long end() {
        mark();
        return interval();
    }

    public long interval() {
        int size = marks.size();
        if (size == 0) return -1;
        if (size == 1) return 0;
        return marks.get(size - 1) - marks.get(0);
    }

    public void reset() {
        start = -1;
        marks.clear();
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }
}
