package com.atguigu.juc.recenter2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);

        /*blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

        /*blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("x");

        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        System.out.println("============");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    }
}
