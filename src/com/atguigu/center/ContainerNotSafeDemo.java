package com.atguigu.center;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 * ArrayList
 */
public class ContainerNotSafeDemo {
    public static void listNotSafe() {
            /* List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);*/

        //   List<String> list = Collections.synchronizedList(new ArrayList<>());//new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <=30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for (int i = 1; i <=30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
        new HashSet<>().add("a");
    }

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <=30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        new HashSet<>().add("a");


        //java.util.ConcurrentModificationException

        /**
         *
         * 1 故障现象java.util.ConcurrentModificationException
         * 2 导致原因
         * 并发争抢修改导致，参考我们的花名册签名情况。
         * 一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常，并发修改异常。
         * 3 解决方案
         * (1)new vector<>();
         * (2)Collections.synchronizedList(new ArrayList<>());
         * (3)new CopyOnWriteArrayList();
         *
         * 4 优化建议（同样的错误不犯第2次）
         *
         *
         *ArrayList.add()//老子还没写完你就抢导致异常
         *
         *
         * 笔记
         * 写时复制
         * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object【】添加，而是先将当前容器Object【】进行Copy，
         * 复制出一个新的容器Object【】newElements，然后新的容器Object【】newElements里添加元素，添加完元素之后，再将愿容器的引用指向新的容器
         * setArray（newElements）；这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite
         * 容器也是一种读写分离的思想，读和写不同的容器。
         *
         *  public boolean add(E e) {
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         try {
         *             Object[] elements = getArray();
         *             int len = elements.length;
         *             Object[] newElements = Arrays.copyOf(elements, len + 1);
         *             newElements[len] = e;
         *             setArray(newElements);
         *             return true;
         *         } finally {
         *             lock.unlock();
         *         }
         *     }
         *
         */
    }
}
