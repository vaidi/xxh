package lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {



        int[] array = {1,2,3};

        int[] ints = Arrays.copyOf(array, 5);
        Arrays.stream(ints).forEach(System.out::println);
        System.out.println(ints);


        ArrayList<String> objects = new ArrayList<>();
        for(int i =0;i<=5;i++){
            new Thread(()->{
                objects.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(objects);
            }).start();
        }




    }
    
    
    
    
}
