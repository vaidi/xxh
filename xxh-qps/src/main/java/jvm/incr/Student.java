package jvm.incr;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Student {



    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        AtomicInteger mm = new AtomicInteger();
        mm.incrementAndGet();
        mm.getAndIncrement();
        PriorityQueue<Student> students = new PriorityQueue<>(( a,b)->b.age-a.age);



    }



    private String name ="xh";
    private int age=222;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
