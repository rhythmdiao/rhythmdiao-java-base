package com.rhythmdiao.util.time;

import java.util.ArrayList;
import java.util.List;

public class TimeCounter {
    private long start = -1;

    private List<Long> marks;

    public TimeCounter() {
        marks = new ArrayList<Long>();
    }

    public TimeCounter start() {
        reset();
        start = currentTime();
        marks.add(start);
        return this;
    }

    public int mark() {
        marks.add(currentTime());
        return marks.size();
    }

    public long end() {
        mark();
        return interval(0, marks.size() - 1);
    }

    public long interval(int start, int end) {
        if (this.start == -1) return 0;
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
