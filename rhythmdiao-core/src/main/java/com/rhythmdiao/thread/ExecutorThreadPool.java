package com.rhythmdiao.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ExecutorThreadPool extends ThreadPool {
    private static final Logger LOG = LoggerFactory.getLogger(ExecutorThreadPool.class);

    private ExecutorService executor;

    public ExecutorThreadPool() {
        super();
        executor = Executors.newFixedThreadPool(thread);
    }

    public ExecutorThreadPool(int thread, long time, TimeUnit timeUnit, ExecutorService executor) {
        super(thread, time, timeUnit);
        this.executor = executor;
    }

    @Override
    public void execute(final Runnable task) {
        if (isShutDown()) {
            LOG.warn("The threadPool has been shut down");
            return;
        }

        try {
            monitor.enterWhen(canSubmit, time, timeUnit);
            currentThreads.incrementAndGet();
            executor.execute(new Runnable() {
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

    @Override
    public <T> Future<T> execute(final Callable<T> task) {
        if (isShutDown()) {
            LOG.info("The threadPool has been shut down");
            return null;
        }

        try {
            monitor.enterWhen(canSubmit, time, timeUnit);
            currentThreads.incrementAndGet();
            return executor.submit(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    try {
                        return task.call();
                    } finally {
                        currentThreads.decrementAndGet();
                    }
                }
            });
        } catch (InterruptedException ignored) {
        }
        return null;
    }

    @Override
    public boolean isShutDown() {
        return executor.isShutdown();
    }

    @Override
    public void shutDown() {
        executor.shutdown();
    }
}
