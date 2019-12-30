package lambda;

import com.google.common.base.Joiner;
import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingUse;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/**
 * 各种lambda的写法
 */
public class LambdaTest {







    @Test
    public void test13(){
        Stream<String> words = Stream.of("Java", "Magazine", "is",
                "the", "best");
        Map<String, Long> letterToCount =
                words.map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .collect(groupingBy(identity(), counting()));
        letterToCount.forEach((key,value)->
                System.out.println("key:"+key+",value:"+value));
    }




    /**
     * 转换流的几种方式
     * 文件也可以转换成流
     */
    @Test
    public void test12() throws IOException {
        Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);
        int[] numbers = {1, 2, 3, 4};
        IntStream numbersFromArray = Arrays.stream(numbers);
        long numberOfLines = Files.lines(Paths.get("C:\\Users\\Administrator\\Desktop\\123.txt"), Charset.defaultCharset()).count();
        System.out.println("文件中的行数："+numberOfLines);
        Stream<Integer> integerStream = Stream.iterate(0, n -> n + 10);
        integerStream.limit(5).forEach(System.out::println); // 0, 10, 20, 30, 40

    }


    /**
     * 计算10到30的奇数流（包含）
     */
    @Test
    public void test11() {
        IntStream oddNumbers =
                IntStream.rangeClosed(10, 30)
                        .filter(n -> n % 2 == 1);
        oddNumbers.mapToObj(e -> e + ",").forEach(System.out::print);
    }


    @Test
    public void test10() {
        List<Integer> numbers = Arrays.asList(new Integer[]{1, 2, 3});
        int product = numbers.stream().reduce(1, (a, b) -> a * b);//计算成绩
        int product2 = numbers.stream().reduce(1, Integer::max);//计算出最大值
        System.out.println("product:" + product + ",product2:" + product2);

    }


    /**
     * 用来验证其是惰性的
     * 即最终的操作会引发短路
     * 使用流通常涉及3件事
     * 1：在其上执行 查询数据源（列入集合）
     * 2：一系列中间操作，形成一条流管道
     * 3：一个终端操作，执行流管道并产生结果
     */
    @Test
    public void test09() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                        .filter(n -> {
                            System.out.println("filtering " + n);
                            return n % 2 == 0;
                        })
                        .map(n -> {
                            System.out.println("mapping " + n);
                            return n * n;
                        })
                        .limit(2)
                        .collect(toList());
        twoEvenSquares.stream().forEach(System.out::print);
    }


    @Test
    public void test08() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        long count = list.stream().filter(x -> {
            list.remove(0);
            return true;
        }).count();
        System.out.println(count);


    }


    /**
     * 用来验证并行流是不安全的
     * 如果用list可能会报错，下标越界什么了
     */
    @Test
    public void test07() {
        // List<Integer> values = new ArrayList<>();
//        Queue<Integer> values = new ConcurrentLinkedQueue<Integer>();
//        IntStream.range(1, 10000).parallel().forEach(values::add);
//        System.out.println(values.size());
        BigDecimal bg = new BigDecimal(1.11000);
        System.out.println(bg.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP));
    }


    @Test
    public void test06() {
        // 设置全局并行流并发线程数  验证这个全局并行并发线程数只能修改一次
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
        System.out.println(ForkJoinPool.getCommonPoolParallelism());// 输出 12
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        System.out.println(ForkJoinPool.getCommonPoolParallelism());// 输出 12
        int availNum = Runtime.getRuntime().availableProcessors();
        System.out.println("当前机器的处理器数量：" + availNum);
    }


    @Test
    public void test05() {
        IntStream list = IntStream.range(0, 10);
        Set<Thread> threadSet = new HashSet<>();
        //开始并行执行
        list.parallel().forEach(i -> {
            Thread thread = Thread.currentThread();
            System.err.println("integer：" + i + "，" + "currentThread:" + thread.getName());
            threadSet.add(thread);
        });
        System.out.println("all threads：" + Joiner.on("，").join(threadSet.stream().map(Thread::getName).collect(toList())));
        String[] array = {"1", "asdas", "yatou", "xinhua", "iloveyou", "我爱你"};
        List asList = Arrays.asList(array);
        Set<Thread> set = new HashSet<>();
        //并行流
        asList.parallelStream().forEach(e -> {
            Thread thread = Thread.currentThread();
            System.err.println("integer：" + e + "，" + "currentThread:" + thread.getName());
            set.add(thread);
        });
        System.out.println("all threads：" + Joiner.on("，").join(set));
        Set<Thread> threads = new HashSet<>();
        //不是并行流
        asList.stream().forEach(e -> {
            Thread thread = Thread.currentThread();
            System.err.println("integer ：" + e + "，" + "currentThread:" + thread.getName());
            threads.add(thread);
        });
        System.out.println("all threads：" + Joiner.on("，").join(threads));

    }


    /**
     * 查询一个list中的每个数据的length的长度
     */
    @Test
    public void test04() {
        String[] strings = {"222", "wlefw", "xinhua", "xiaozhu", "erlong", "yuanerlong", "yuanmu"};
        OptionalInt maxLenth = Arrays.asList(strings).stream().filter(e -> e.startsWith("y")).mapToInt(String::length).min();
        if (maxLenth.isPresent()) {
            System.out.println(maxLenth.getAsInt());
        }
    }


    @Test
    public void test03() {
        // 使用forEach()结合Lambda表达式迭代Map
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }


    @Test
    public void test02() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too", "yat", "x"));
        list.forEach(e -> e = e + "袁二龙");
        list.forEach(e -> System.out.println("eeeee:" + e));
        list.removeIf(e -> e.length() > 4);//删除长度大于 3的
        list.forEach(e -> System.out.println("删除后的数据：" + e));
        list.replaceAll(e -> e.toUpperCase());
        list.forEach(e -> System.out.println("字母大写后的：" + e));
        list.sort((str1, str2) -> str1.length() - str2.length());
        list.forEach(e -> System.out.println("排序后的字母：" + e));

    }


    @Test
    public void test01() {
        Runnable runnable = () -> System.out.println("用来验证lambda的表达式");
        ActionListener actionListener = event -> {
        };
        BinaryOperator<Long> binaryOperator = (x, y) -> x + y;
        MyStream<String> myStream = new MyStream<>();
        myStream.forEach(str -> System.out.println("结果：" + str));
    }


}

class MyStream<T> {

    private List<T> list;


    public void forEach(PConsumer<T> t) {
        System.out.println("ttt:" + t);
//        for (T t1 : list) {
//            t.accept(t1);
//        }
    }


}