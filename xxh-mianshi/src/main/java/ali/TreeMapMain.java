package ali;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class TreeMapMain {


    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        Random random = new Random();
        IntStream.rangeClosed(1, 20).forEach(e -> {
            int intRandom = random.nextInt(100);
            System.out.println("intRandom:" + intRandom);
            treeMap.put("key:" + intRandom, e + "");
        });

        System.out.println("key:" + treeMap.firstKey()+",lastKey:"+ treeMap.lastKey());


//        List<Node> list = new ArrayList<>();
//        IntStream.rangeClosed(1,10).forEach(e->{
//            System.out.println("e:"+e);
//            Node node = new Node("xxh"+e,e);
//            list.add(node);
//        });
//        System.out.println("size:"+list.size());
//        Shard<Node> shard = new Shard(list);
//        Node node = shard.getShardInfo("xxh102");
//        System.out.println(node.toString());


    }


}
