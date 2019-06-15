package com.atguigu.juc.rejuc.day02;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        FutureTask futureTask = new FutureTask(demo);

        new Thread(futureTask).start();

        try {
            int result = (int)futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class ThreadDemo implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <=10; i++) {
            sum += i;
        }
        return sum;
    }
}