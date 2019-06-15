package com.atguigu.juc.recenter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            while(number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " : " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception{
        lock.lock();
        try {
            while(number == 0) {
                try{
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            number--;
            System.out.println(Thread.currentThread().getName() + " : " + number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "CC").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "DD").start();
    }
}
