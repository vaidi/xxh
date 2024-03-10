package risk;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        String mm = "serwerwererlong";
        System.out.println(mm.contains("erlong"));

        // 创建一个基于大顶堆的优先队列
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        // 添加元素到队列中
        queue.add(10);
        queue.add(20);
        queue.add(15);
        // 打印队列中的元素
        while (!queue.isEmpty()) {
            System.out.println(queue.poll()); // 移除并打印队列中的最小元素
        }
    }
}
