package lambda.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class Psorting {

    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red")));
        inventory.sort(new AppleSort());
        inventory.stream().forEach(System.out::print);
        System.out.println();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple apple, Apple t1) {
                return  t1.getWeight()-apple.getWeight();
            }
        });
        inventory.stream().forEach(System.out::print);
        System.out.println();
        inventory.sort(comparing(Apple::getWeight).reversed());
        inventory.stream().forEach(System.out::print);
        
    }


    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class AppleSort implements Comparator<Apple> {
        @Override
        public int compare(Apple t1, Apple t2) {
            return t1.getWeight().compareTo(t2.getWeight());
        }
    }


}
