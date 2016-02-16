package com.rhythmdiao.util.time;

import java.util.concurrent.LinkedBlockingQueue;

public class TimeMonitor {
    private LinkedBlockingQueue<Long> timeList;

    public TimeMonitor() {
        this.timeList = new LinkedBlockingQueue<Long>(1000);
    }

    public long record(long interval) {
        if (timeList.remainingCapacity() <= 0) {
            timeList.poll();
        }
        timeList.add(interval);
        return interval;
    }

    public long avg() {
        int size = timeList.size();
        if (timeList.isEmpty()) {
            return 0L;
        } else {
            long sum = 0L;
            for (long time : timeList) {
                sum += time;
            }
            return sum / size;
        }
    }

    public void clear() {
        timeList.clear();
    }
}
