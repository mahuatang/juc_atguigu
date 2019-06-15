package com.atguigu.juc.recenter2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        while(!threadAtomicReference.compareAndSet(null, thread)){

        }
        System.out.println(Thread.currentThread().getName() + "拿到锁");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "释放锁");
    }

    public static void main(String[] args) throws Exception{
        SpinLockDemo demo = new SpinLockDemo();
        new Thread(() -> {
            demo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e) {
                e.printStackTrace();
            }
            demo.myUnLock();
        }, "AA").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            demo.myLock();
            demo.myUnLock();
        }, "BB").start();
    }
}
