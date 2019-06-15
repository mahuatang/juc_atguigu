package com.atguigu.center;

public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法Demo");
    }

    //DCL(Double check Lock双端检索机制)
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
           // instance = new SingletonDemo();
        }
        return instance;
    }
    public static void main(String[] args) {
        //单线程（main线程的操作动作......）
       // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
       // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
       // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //并发多线程后，情况发生了很大的变化
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {SingletonDemo.getInstance();},String.valueOf(i)).start();
        }

    }
}
