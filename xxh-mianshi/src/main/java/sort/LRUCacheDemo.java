package sort;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheDemo {

    private int cacheSize;

    private Map<Integer,Node<Integer,Integer>> map;

    private DoubleLinkList<Integer,Integer> doubleLinkList;

    public LRUCacheDemo(int cacheSize){
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        doubleLinkList = new DoubleLinkList<>();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node<Integer, Integer> integerIntegerNode = map.get(key);
        doubleLinkList.removeNode(integerIntegerNode);
        doubleLinkList.addHead(integerIntegerNode);
        return integerIntegerNode.v;

    }

    public void put(int key,int value){
        if(map.containsKey(key)){
            Node<Integer, Integer> integerIntegerNode = map.get(key);
            doubleLinkList.removeNode(integerIntegerNode);
            doubleLinkList.addHead(integerIntegerNode);
            integerIntegerNode.v = value;
        }else{
            if(map.size() == cacheSize){
                Node tail = doubleLinkList.getTail();
                doubleLinkList.removeNode(tail);
                map.remove(tail.k);
            }
            Node node = new Node(key, value);
            map.put(key,node);
            doubleLinkList.addHead(node);
        }



    }





    public static void main(String[] args) {
        new HashMap();
    }



    class Node<K,V>{
        K k;
        V v;
        Node prev;
        Node next;

        public Node(){
            this.prev = this.next = null;
        }

        public Node(K key,V value){
            this.k = key;
            this.v = value;
            this.prev = this.next = null;
        }
    }

    class DoubleLinkList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        public DoubleLinkList(){
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        public void addHead(Node node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public void removeNode(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        public Node getTail(){
            return tail.prev;
        }



    }

}
