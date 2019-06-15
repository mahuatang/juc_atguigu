package com.atguigu.juc.recenter2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "come in Callable");
        return 1024;
    }
}

/*class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "come in runnable");
        System.out.println("2016");
    }
}*/

public class CallableDemo {
    public static void main(String[] args) throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
       // MyThread futureTask = new MyThread();
        new Thread(futureTask, "t1").start();
        new Thread(futureTask, "t2").start();

        //System.out.println("$$$$$$$" + futureTask.get());
    }
}
