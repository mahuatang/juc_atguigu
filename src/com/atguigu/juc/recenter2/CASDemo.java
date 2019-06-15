package com.atguigu.juc.recenter2;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(50);

        System.out.println(atomicInteger.compareAndSet(50, 100) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(50, 102) + "\t" + atomicInteger.get());
    }
}
