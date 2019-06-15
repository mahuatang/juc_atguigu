package com.atguigu.juc.recenter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("集齐7颗龙珠， 可以召唤神龙啦！");
            }
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("集齐了" + Thread.currentThread().getName() + "龙珠");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }, String.valueOf(i)).start();
        }
    }
}
