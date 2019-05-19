package com.atguigu.center;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t ######invoked sendEmail()");

    }

    //------------------------------------------------

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();//lock有几把，unlock就应该有几把
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");
        } finally {
            lock.unlock();
        }
    }

}

/**
 * case one Synchronized就是一个典型的可重入锁
 * t1	 invoked sendSMS()
 * t1	 ######invoked sendEmail()
 * t2	 invoked sendSMS()
 * t2	 ######invoked sendEmail()//在外层方法获得锁之后，内层方法自动获得锁
 *
 * case two
 * Reen
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");

        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");

        t3.start();
        t4.start();

    }
}
