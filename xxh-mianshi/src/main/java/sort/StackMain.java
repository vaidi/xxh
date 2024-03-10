package sort;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class StackMain {


    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        MyQueue queue = new MyQueue(10);
        IntStream.range(0,10).forEach(e->queue.push(e));
        IntStream.range(0,10).forEach(e-> System.out.print(queue.pop()));
        LinkedList<Object> objects = new LinkedList<>();
        IntStream.range(1, 20).forEach(e -> objects.push(e));
        System.out.println(JSON.toJSONString(objects));
        IntStream.range(1, 20).forEach(e -> System.out.print(objects.pop()));
        List<String> list = Lists.newArrayList("{[]}", "[]{}", "[[[", "}}}", "[{}");
        for (String s : list) {
            System.out.println(isValid(s));
        }


    }


    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> linkedList = new LinkedList();
        if (switchCa(chars[0]) == null) {
            return false;
        }
        for (int i = 0; i < chars.length; i++) {
            if (switchCa(chars[i]) == null && linkedList.isEmpty()) {
                return false;
            } else if (switchCa(chars[i]) != null) {
                linkedList.push(switchCa(chars[i]));
            } else if (switchCa(chars[i]) == null && !linkedList.isEmpty()) {
                char aChar = chars[i];
                Character pop = linkedList.pop();
                if (new Character(aChar).compareTo(pop) != 0) {
                    return false;
                }
            }
        }
        return linkedList.isEmpty();

    }

    public static Character switchCa(Character c) {
        switch (c) {
            case '{':
                return '}';
            case '[':
                return ']';
            default:
                return null;

        }
    }

    /**
     * 小顶堆
     */
    static class MinHeap{





    }


    static class MyQueue {
        private LinkedList<Integer> left = new LinkedList();
        private LinkedList<Integer> right = new LinkedList();
        private int capition = 10;
        private int size = 0;
        public MyQueue(int capition) {
            this.capition = capition;
        }
        void push(int x) {
            while(!left.isEmpty()){
                right.push(left.pop());
            }
            right.push(x);
            while (!right.isEmpty()) {
                Integer pop = right.pop();
                left.push(pop);
            }
        }
        int pop() {
            return left.pop();
        }
        int peek() {
            return left.peek();
        }
        boolean empty() {
            return size == 0;
        }

    }


}
