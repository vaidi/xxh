package lambda.chap0;

import lambda.chap4.Dish;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.*;

public class MainTest3 {

    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish.get().toString());
        List<BigDecimal> list = new ArrayList();
        list.add(new BigDecimal(5));
        list.add(new BigDecimal(15));
        list.add(new BigDecimal(25));
        list.stream().reduce(BigDecimal::max).ifPresent(e -> System.out.println("当前中的最大值:" + e));
        list.stream().reduce((a,b)->a.compareTo(b)<1?a:b).ifPresent(e-> System.out.println("找出来最大的:"+e));
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories:"+totalCalories);
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("平均值avgCalories:"+avgCalories);
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics,个数，平均值，最小，和，最大:"+menuStatistics);
        String shortMenu = menu.stream().map(Dish::getName).collect(joining("菜单："));
        System.out.println("shortMenu:"+shortMenu);
        int totalCalories1 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("totalCalories1:"+totalCalories1);
    }

}
