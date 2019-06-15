package com.atguigu.juc.rejuc.day02;

import java.util.concurrent.locks.Condition;

public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor, "P1").start();
        new Thread(consumer, "C1").start();

        new Thread(productor, "P2").start();
        new Thread(consumer, "C2").start();
    }
}

/*class Clerk {
    public int number = 0;
    public synchronized void get() {
        try {
            while(number >= 1) {
                System.out.println("产品已满");
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + " : " + (++number));
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void sale() {
        try {
            while(number <= 0) {
                System.out.println("没有产品");
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + " : " + (--number));
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/

/*class Productor implements Runnable{
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
}*/
