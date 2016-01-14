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
        return interval(0, marks.size() - 1);
    }

    public long interval(int start, int end) {
        if (end == -1 || end < start) return -1;
        if (end == start) return 0;
        return marks.get(end) - marks.get(start);
    }

    public void reset() {
        start = -1;
        marks.clear();
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }
}
