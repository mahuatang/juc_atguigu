package com.atguigu.juc.recenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        lock.writeLock().lock();
        try {
            System.out.println("开始写入：" + key + ", " + value);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println("写入完成" + key + " : " + value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println("开始读取：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("读取完成：" + map.get(key));
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                myCache.put(Thread.currentThread().getName(), Thread.currentThread().getName());
            }, i + "").start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                myCache.get(Thread.currentThread().getName());
            }, i + "").start();
        }
    }
}
