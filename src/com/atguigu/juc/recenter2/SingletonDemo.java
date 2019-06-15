package com.atguigu.juc.recenter2;

public class SingletonDemo {
    private volatile static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println("SingletonDemo构造方法得到调用");
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }).start();
        }
    }
}