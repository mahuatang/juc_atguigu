package com.atguigu.juc.rejuc.day02;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.set(new Random().nextInt(10));
                }
            }, "CC").start();
        }


        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.get();
                }
            }).start();
        }
    }
}

class ReadWriteLockDemo {
    public int number;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void set(int number) {
        lock.writeLock().lock();
        this.number = number;
        System.out.println("set");
        lock.writeLock().unlock();
    }

    public void get() {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " : " + number);
        lock.readLock().unlock();
    }
}

