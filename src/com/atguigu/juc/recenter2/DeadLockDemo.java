package com.atguigu.juc.recenter2;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "持有" + lockA + "........");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "持有" + lockB + "$$$$$$$");
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        HoldLockThread threadA = new HoldLockThread(lockA, lockB);
        HoldLockThread threadB = new HoldLockThread(lockB, lockA);

        new Thread(threadA, "hhhhA").start();
        new Thread(threadB, "aaaaB").start();
    }
}
