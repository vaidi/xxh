package lambda.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PLambdas {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));
        Collection c1 =filter(inventory, e->e.color.equals("green"));
        c1.stream().forEach(System.out::println);
        Collection c2 =filter(inventory,Apple::isToBig);
        c2.stream().forEach(System.out::println);
    }
    
    
    
    public static List<Apple> filter(List<Apple> apples,Filter filter){
        List<Apple> collection = new ArrayList<>();
        for (Apple apple : apples) {
            if(filter.test(apple)){
                collection.add(apple);
            }
        }
        return collection;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";
        private boolean toBig= true;

        public boolean isToBig() {
            return toBig;
        }

        public void setToBig(boolean toBig) {
            this.toBig = toBig;
        }

        public Apple(int weight, String color){
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
    interface Filter{
        boolean test(Apple apple);
    }

}


