package com.erlong.config.thread;

import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name ="yel";

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
