package com.atguigu.juc.rejuc.day02;

import java.util.Random;
import java.util.concurrent.*;

public class TestScheduledThreadPool {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 5; i++) {
            Future future = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int number = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " , " + number);
                    return number;
                }
            }, 1, TimeUnit.SECONDS);

            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
