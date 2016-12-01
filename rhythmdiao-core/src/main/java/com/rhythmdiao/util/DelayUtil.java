package com.rhythmdiao.util;

public enum DelayUtil {
    I;

    public void delay() {
        delay(1000);
    }

    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
