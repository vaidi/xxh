package lambda.chap0;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.LongStream;

public class MainTest6 {


    public static void main(String[] args) {
        int process = Runtime.getRuntime().availableProcessors();
        System.out.println("process:"+process);
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, 10_000_000).parallel().forEach(accumulator::add);
        System.out.println(accumulator.total);
        int countWords = countWordsIteratively("good");
        System.out.println("countWords:"+countWords);

    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
}
class Accumulator{
    public AtomicLong total = new AtomicLong(0);

    public void add(Long atomicInteger){
         total.addAndGet(atomicInteger.intValue());
    }



}













class ToListCollector<T> implements Collector<T, List<T>, List<T>> {


    @Override
    public Supplier<List<T>> supplier() {
        //  return ()->new ArrayList<T>();
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        // return (list,item)->list.add(item);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}