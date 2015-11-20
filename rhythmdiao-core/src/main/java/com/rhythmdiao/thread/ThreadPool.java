package com.rhythmdiao.thread;

import com.google.common.annotations.Beta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Beta
public class ThreadPool {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadPool.class);

    private int nThreads;
    
    private ExecutorService executorService;

    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.executorService = Executors.newFixedThreadPool(nThreads);
    }

    public ThreadPool(int nThreads, ExecutorService executorService) {
        this.nThreads = nThreads;
        this.executorService = executorService;
    }

    public void execute(final Runnable task) {
        if (executorService.isShutdown()) {
            LOG.info("The threadPool has been shut down");
            return;
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                task.run();
            }
        });
    }

    public boolean isShutDown() {
        return executorService.isShutdown();
    }

    public void shutDown() {
        executorService.shutdown();
    }
}
