package lambda.chap0;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainTest2 {


    public static void main(String[] args) {

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );

       // pythagoreanTriples.forEach(System.out::println);
        pythagoreanTriples.limit(500)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
        Stream.iterate(0,e->e+2).sorted().forEach(System.out::println);
    }

}
