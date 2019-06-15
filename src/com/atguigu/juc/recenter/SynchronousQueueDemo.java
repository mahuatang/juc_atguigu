package com.atguigu.juc.recenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    private static BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "put a");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "put b");
                blockingQueue.put("c");
                System.out.println(Thread.currentThread().getName() + "put c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "take " + blockingQueue.take());
                System.out.println(Thread.currentThread().getName() + "take " + blockingQueue.take());
                System.out.println(Thread.currentThread().getName() + "take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();

    }
}
