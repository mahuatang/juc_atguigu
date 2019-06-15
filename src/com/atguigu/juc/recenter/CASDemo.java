package com.atguigu.juc.recenter;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println(atomicInteger.compareAndSet(10, 20) + " : " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(10, 80) + " : " + atomicInteger.get());


    }
}
