package com.atguigu.juc.recenter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncAndReentrantLockDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private volatile int number = 1;

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
            number = 2;
            condition2.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
            number = 3;
            condition3.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
            number = 1;
            condition.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SyncAndReentrantLockDemo syncAndReentrantLockDemo = new SyncAndReentrantLockDemo();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                syncAndReentrantLockDemo.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                syncAndReentrantLockDemo.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                syncAndReentrantLockDemo.print15();
            }
        }, "CC").start();
    }
}
