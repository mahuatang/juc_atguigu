package com.atguigu.juc.recenter;

import java.util.concurrent.CountDownLatch;

public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "---->Singleton Demo得到调用");
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        //    instance = new SingletonDemo();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                instance = SingletonDemo.getInstance();
                System.out.println(Thread.currentThread().getName() + " : " + instance.toString());
            }, i + "").start();
        }

        countDownLatch.await();
    }
}
