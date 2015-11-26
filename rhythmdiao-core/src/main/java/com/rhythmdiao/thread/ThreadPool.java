package com.rhythmdiao.thread;

import com.google.common.annotations.Beta;
import com.google.common.util.concurrent.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Beta
public class ThreadPool {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadPool.class);
    private AtomicInteger currentThreads = new AtomicInteger();
    private final Monitor monitor = new Monitor();
    private long time;
    private TimeUnit timeUnit;
    private int thread;
    private ExecutorService executorService;

    private final Monitor.Guard canSubmit = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return currentThreads.get() < thread;
        }
    };

    public ThreadPool(int thread) {
        this.thread = thread;
        this.executorService = Executors.newFixedThreadPool(thread);
        this.time = 10;
        this.timeUnit = TimeUnit.MILLISECONDS;
    }

    public ThreadPool() {
    }

    public void execute(final Runnable task) {
        if (executorService.isShutdown()) {
            LOG.info("The threadPool has been shut down");
            return;
        }
        try {
            monitor.enterWhen(canSubmit, time, timeUnit);
            currentThreads.incrementAndGet();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } finally {
                        currentThreads.decrementAndGet();
                    }
                }
            });
        } catch (InterruptedException ignored) {
        }
    }

    public ThreadPool timeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public ThreadPool time(long time) {
        this.time = time;
        return this;
    }

    public ThreadPool executorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    public boolean isShutDown() {
        return executorService.isShutdown();
    }

    public void shutDown() {
        executorService.shutdown();
    }
}
