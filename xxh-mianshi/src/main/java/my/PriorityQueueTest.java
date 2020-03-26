package my;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.UUID;

public class PriorityQueueTest {
    public static void main(String[] args) {

        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        Random random = new Random();
       for(int i=0;i<100;i++){
           q.offer(random.nextInt(10000));
       }
        for(int i=0;i<100;i++){
            System.out.println(q.poll());
        }


    }

}
