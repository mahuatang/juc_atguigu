package com.atguigu.juc.recenter;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            int flag = i;
            new Thread(() -> {
                System.out.println(CountryEnum.foreach_CountryEnum(flag).getMessage() + "国被灭");
                countDownLatch.countDown();
            }, i + "").start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "秦国统一天下");
        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getMessage());

    }

    public static void closeDoor() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println("第" + Thread.currentThread().getName() + "个同学离开教室");
                countDownLatch.countDown();
            }, i + "").start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "班长最后关门走人");
    }
}
