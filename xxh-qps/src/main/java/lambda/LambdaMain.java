package lambda;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 14:49
 * @Description:
 */
public class LambdaMain {











    @Test
    public void testReduce(){
        List<Integer> numbers = new ArrayList<>();
        for(int i=0;i<2;i++){
            numbers.add(i);
        }
        int sum = numbers.stream().reduce(0, Integer::sum);
        int sum2 = numbers.parallelStream().reduce(0, Integer::sum);
        System.out.println("num:"+sum+"sum2:"+sum2);

    }














    public static void main(String[] args) {
        List list = new ArrayList();
        Optional optional = list.stream().filter(e->e.toString().equals("1")).findAny();
        if(optional.isPresent()){
            System.out.println("optional:"+optional.get());
        }

        /*List<Integer> arrayList = new Vector <>(Arrays.asList(1,2,3,4,5));//arrayList 与这个vector都不能保证下面的remove不报错
        Collections.synchronizedList(arrayList);
        long count = arrayList.stream().filter(x -> {arrayList.remove(0);return true;})
                .count();
        System.out.println(count);*/

        List<String> l = new ArrayList(Arrays.asList("one", "two"));
        Stream<String> sl = l.stream();
        l.add("three");
        String s = sl.collect(joining("xxh"));
        System.out.println("ssssss:"+s);


        System.out.println(2<<2);

        for(int i=0;i<5;++i){
            System.out.println("i::::::"+i);
        }

        for(int j=0;j<5;j++){
            System.out.println("i::::::"+j);
        }







    }


}
