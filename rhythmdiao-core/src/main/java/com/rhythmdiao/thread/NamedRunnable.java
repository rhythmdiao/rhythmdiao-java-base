package com.rhythmdiao.thread;

public final class NamedRunnable implements Runnable {
    private final String name;
    private final Runnable task;

    private NamedRunnable(String name, Runnable runnable) {
        this.name = name;
        this.task = runnable;
    }

    public static Runnable NamedRunnable(final String name, final Runnable runnable) {
        return new NamedRunnable(name, runnable);
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        String prev = thread.getName();
        thread.setName(this.name);
        task.run();
        thread.setName(prev);
    }
}
