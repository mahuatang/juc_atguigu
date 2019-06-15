package com.atguigu.juc.recenter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo {

    public static void listNotSafe() {
        /* List<String> list = Arrays.asList("a", "b", "c");
       list.forEach(System.out::println);*/

        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new ArrayList<>();
        for (int i = 1; i <=30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, i + "").start();
        }
    }

    public static void mapNotSafe() {
        Map map = new ConcurrentHashMap();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0, 8), "a");
                System.out.println(map);
            }, i + "").start();

        }
    }



    public static void main(String[] args) throws Exception{
      //  listNotSafe();
      //  setNotSafe();
        mapNotSafe();
    }
}
