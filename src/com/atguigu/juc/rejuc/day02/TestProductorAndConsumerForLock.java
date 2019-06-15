package com.atguigu.juc.rejuc.day02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumerForLock {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor, "p").start();
        new Thread(consumer, "c").start();

        new Thread(productor, "p2").start();
        new Thread(consumer, "c2").start();
    }
}

class Clerk {
    public int number = 0;
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public void get() {
        lock.lock();
        try {
            while(number >= 1) {
                System.out.println("产品已满");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " : " + (++number));
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sale() {
        lock.lock();
        try {
            while(number <= 0) {
                System.out.println("没有产品");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " : " + (--number));
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Productor implements Runnable{
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                Thread.sleep(200);
                clerk.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                Thread.sleep(200);
                clerk.sale();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
