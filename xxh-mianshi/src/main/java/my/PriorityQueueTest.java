package my;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueTest {

    private volatile String[] mm;

    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            q.offer(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(q.poll());
        }


    }

}
