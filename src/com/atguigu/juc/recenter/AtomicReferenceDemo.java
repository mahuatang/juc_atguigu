package com.atguigu.juc.recenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@AllArgsConstructor
@ToString
class User {
    String name;
    int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User user = new User("z3", 22);
        User user2 = new User("li4", 25);

        userAtomicReference.set(user);
        System.out.println(userAtomicReference.compareAndSet(user, user2) + "\t" + userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(user, user2) + "\t" + userAtomicReference.get().toString());
    }
}
