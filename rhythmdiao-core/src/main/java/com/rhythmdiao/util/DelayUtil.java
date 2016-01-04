package com.rhythmdiao.util;

public enum DelayUtil {
    ;

    public static void delay() {
        delay(1000);
    }

    public static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
