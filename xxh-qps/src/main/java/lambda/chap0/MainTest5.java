package lambda.chap0;

import lambda.chap4.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class MainTest5 {

    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;
        /**
         * 1:收集器用虚线 表示，因此groupingBy是最外层，根据菜肴的类型把菜单流分组，得到三个子流
         */
        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("分区partitionedMenu:"+partitionedMenu.toString());

        System.out.println("############################################################");
        Stream<Integer> integerStream = IntStream.rangeClosed(1,12).boxed();

















    }

}
