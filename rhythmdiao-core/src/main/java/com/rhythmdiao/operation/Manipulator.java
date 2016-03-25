package com.rhythmdiao.operation;

public abstract class Manipulator<T> {
    public abstract T execute();

    public abstract boolean isDone();
}
