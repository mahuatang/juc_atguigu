package com.atguigu.juc.recenter2;

import org.junit.internal.runners.statements.RunAfters;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                atomicReference.compareAndSet(100, 101);
                atomicReference.compareAndSet(101, 100);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicReference.compareAndSet(100, 2019);
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicReference.get());

        System.out.println("==============================================");

        new Thread(new Runnable() {
            @Override
            public void run() {
                atomicStampedReference.compareAndSet(100, 102, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + " : " + atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(102, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + " : " + atomicStampedReference.getStamp());
            }
        }, "t3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean flag = atomicStampedReference.compareAndSet(100, 2019, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "修改成功与否：" + flag);
            }
        }, "t4").start();
    }
}
