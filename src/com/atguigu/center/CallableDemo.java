package com.atguigu.center;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*class MyThread implements Runnable {
    @Override
    public void run() {

    }
}*/

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("********************* come in Callable");
        //暂停一会儿
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception{
        //两个线程，一个main主线程，一个是AAfutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        //只会算一次
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();

        int result01 = 100;
        /*while (!futureTask.isDone()) {

        }*/
        int result02 = futureTask.get();//如无必要，建议放在最后，要求获取Callable线程的计算结果，
        // 如果没有计算完成就要去强求，会导致阻塞，直到计算完成。

        System.out.println("****************result: " + (result01 + result02));
    }
}
