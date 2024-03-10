package lock.syn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class ConHashMap {

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null,null);



        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap();
        IntStream.range(0, 1000).forEach(e -> {
            new Thread(()->{
                concurrentHashMap.put(e, UUID.randomUUID().toString().substring(0, 10));
            }).start();
        });
        System.out.println(Arrays.asList(concurrentHashMap.keys()).size());


    }

}
