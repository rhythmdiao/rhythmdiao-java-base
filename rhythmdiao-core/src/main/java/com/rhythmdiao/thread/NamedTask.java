package com.rhythmdiao.thread;

public class NamedTask implements Runnable {
    private String name;
    private Runnable task;

    public NamedTask(String name, Runnable task) {
        this.name = name;
        this.task = task;
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
