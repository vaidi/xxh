package lock.syn;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWrite {


    public static void main(String[] args) {

        CopyOnWriteArraySet<String> objects = new CopyOnWriteArraySet<>();
        String[] arr = {"12","12","12"};
        for (String s : arr) {
            objects.add(s);
        }
        System.out.println(objects);
        Queue<String> queue = new ArrayBlockingQueue<>(1);
//        queue.add("abc");
//        System.out.println(queue.offer("23"));
        for (String s : queue) {
            System.out.println("循环:"+s);
        }
        String poll = queue.poll();
        System.out.println("poll:"+poll);


    }

}
