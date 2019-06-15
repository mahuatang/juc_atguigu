package com.atguigu.juc.rejuc.day02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可能写的有问题，容易被单线程独占
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }
}

class Ticket implements Runnable{
    private int number = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "售票完成，余票为：" + --number);

                }
            }finally {
                lock.unlock();
            }
        }
    }
}
