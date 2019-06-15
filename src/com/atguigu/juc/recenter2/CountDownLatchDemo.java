package com.atguigu.juc.recenter2;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = CountryEnum.forEachCountry(Integer.valueOf(Thread.currentThread().getName())).getValue();
                    System.out.println(name + "被灭");
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("六国统一");
    }
}
