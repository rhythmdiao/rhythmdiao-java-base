package com.rhythmdiao.thread;

import com.google.common.util.concurrent.Monitor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ThreadPool {
    protected AtomicInteger currentThreads = new AtomicInteger();
    protected final Monitor monitor = new Monitor();
    protected int thread;
    protected long time;
    protected TimeUnit timeUnit;

    protected final Monitor.Guard canSubmit = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return currentThreads.get() < thread;
        }
    };

    public ThreadPool() {
        thread = Runtime.getRuntime().availableProcessors();
        time = 10L;
        timeUnit = TimeUnit.MILLISECONDS;
    }

    public ThreadPool(int thread, long time, TimeUnit timeUnit) {
        this.thread = thread;
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public abstract void execute(final Runnable task);

    public abstract <T> Future<T> execute(final Callable<T> task);

    public abstract boolean isShutDown();

    public abstract void shutDown();
}
