package lambda.chap0;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MainTest {


    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
        map.put("origin", Origin::new);
    }

    public static Fruit giveMeFruit(String fruit, int weight) {
        return map.get(fruit.toLowerCase()).apply(weight);
    }
    public static void main(String[] args) {


    }


}

class Fruit {
    protected int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class Origin extends Fruit {
    public Origin() {
    }

    public Origin(int weight) {
        this.weight = weight;
    }
}

class Apple extends Fruit {
    public Apple(int weight) {
        this.weight = weight;
    }
}