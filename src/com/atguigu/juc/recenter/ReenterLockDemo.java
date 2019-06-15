package com.atguigu.juc.recenter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "sendSMS invoked");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "sendEmail invoked");
    }

    Lock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ", get() invoked");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ", set() invoked");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }
}

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone).start();
    }
}
