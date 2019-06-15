package com.atguigu.juc.rejuc.day01;

public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap compareAndSwap = new CompareAndSwap();

        for(int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int value = compareAndSwap.get();
                    boolean b = compareAndSwap.compareAndSet(value, (int)(Math.random() * 101));
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class CompareAndSwap {
    private int value = 0;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedVaue, int newValue) {
        int oldValue = value;

        if (oldValue == expectedVaue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
