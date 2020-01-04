package lambda.chap5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionDemo {

    public static <T, R> List<R> getList(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.addAll(Arrays.asList("1", "2", "3", "4", "5"));
        List<Object> result = getList(strings,
                (String e) -> {
                    System.out.println("threadName:" + Thread.currentThread().getName());
                    return e.length();
                }
        );
        result.parallelStream().forEach((Object e)-> {System.out.println("线程："+Thread.currentThread().getName()+";w:"+e);});
        Integer num = 100;
        int num1 = 100;
        Integer num2 = 120;
        int num3 = 120;

        System.out.println(num == num1);
        System.out.println(num2 == num3);


    }


}
