package com.atguigu.juc.recenter2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    public static volatile boolean FLAG = true;
    public AtomicInteger atomicInteger = new AtomicInteger(0);
    public BlockingQueue blockingQueue = new ArrayBlockingQueue(6);

    public void myProd() {
        while (FLAG) {
            String data = atomicInteger.incrementAndGet() + "";
            blockingQueue.offer(data);
            System.out.println("生产：" + data);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("生产停止");
    }

    public void myConsumer() {
        while (FLAG) {
            try {
                String data = (String)blockingQueue.poll(2, TimeUnit.SECONDS);
                if (data == null) {
                    FLAG = false;
                } else {
                    System.out.println("消费：" + data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费中断");
    }

    public void myStop() {
        FLAG = false;
    }
}



public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myResource.myProd();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myResource.myConsumer();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("大老板叫暂停");

        myResource.myStop();
    }

}
