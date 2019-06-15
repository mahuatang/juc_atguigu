package com.atguigu.juc.recenter2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user1 = new User("li3", 10);
        User user2 = new User("zi4", 20);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);
        System.out.println("成功与否：" + atomicReference.compareAndSet(user1, user2) + "\t" + atomicReference.get().toString());
        System.out.println("成功与否：" + atomicReference.compareAndSet(user1, user2) + "\t" + atomicReference.get().toString());
    }
}
