package com.atguigu.juc.recenter2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    public Lock lock = new ReentrantLock();
    public int number = 0;
    public Condition condition = lock.newCondition();

    public void prod() {
        lock.lock();

        if (number >= 1) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        number++;
        System.out.println(Thread.currentThread().getName() + "生产成功");
        condition.signal();
        lock.unlock();
    }

    public void consumer() {
        lock.lock();

        if (number <= 0) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "消费成功");
        condition.signal();
        lock.unlock();
    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                shareData.prod();
            }, "t1").start();
        }

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                shareData.consumer();
            }, "t2").start();
        }


        /*new Thread(() -> {
            shareData.prod();
        }, "t3").start();

        new Thread(() -> {
            shareData.consumer();
        }, "t4").start();*/
    }
}
