package com.atguigu.juc.recenter2;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo {
    public void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(list);
                }
            }, String.valueOf(i)).start();
        }
    }

    public void setNotSafe() {
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(set);
                }
            }, String.valueOf(i)).start();
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(map);
                }
            }, String.valueOf(i)).start();
        }
    }
}
