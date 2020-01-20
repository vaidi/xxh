package lambda.chap0;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MainTest1 {


    public static void main(String[] args) {


        System.out.println(9.2%1);



        List<String> words = Arrays.asList(new String[]{"hello", "world"});

        words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList()).forEach(System.out::println);

    }

}
