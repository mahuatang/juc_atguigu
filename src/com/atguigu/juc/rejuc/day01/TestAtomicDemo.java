package com.atguigu.juc.rejuc.day01;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable {
   // public volatile int serialNumber = 0;
    public AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}