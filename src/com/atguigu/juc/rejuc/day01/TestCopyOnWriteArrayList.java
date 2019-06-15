package com.atguigu.juc.rejuc.day01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        HelloThread ht = new HelloThread();
        for(int i = 0; i < 10; i++) {
            new Thread(ht).start();
        }
    }
}

class HelloThread implements Runnable {
 //   private static CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    private static List list = Collections.synchronizedList(new ArrayList());

    static {
        list.add("a");
        list.add("b");
        list.add("c");
    }

    @Override
    public void run() {
        Iterator<String> iter = list.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
            list.add("AA");
        }
    }
}
