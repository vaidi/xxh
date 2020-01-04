package design.strategy;

import java.util.ArrayList;

public class StrategyPattern {

    public static void main(String[] args) {

        BillingStrategy normalStrategy = BillingStrategy.normalStrategy();
        BillingStrategy happyHourStrategy = BillingStrategy.happyHourStrategy();

        Customer firstCustomer = new Customer(normalStrategy);
        firstCustomer.add(100, 1);
        firstCustomer.setStrategy(happyHourStrategy);
        firstCustomer.add(100, 2);
        firstCustomer.printBill();
        //new Customer
        Customer secondCustomer = new Customer(happyHourStrategy);
        secondCustomer.add(80, 1);

        //End Happy Hour
        secondCustomer.setStrategy(normalStrategy);
        secondCustomer.add(130, 2);
        secondCustomer.add(250, 1);
        secondCustomer.printBill();

    }


}

class Customer {


    public static void main(String[] args) {
        BillingStrategy strategy = (int m) -> {
            System.out.println("打印我这一句话");
            return m * 100;
        };
        int num = strategy.getActPrice(3);
        int num1 = strategy.defaultStrategy().getActPrice(3);
        System.out.println("num:" + num + ",,num1:" + num1);
    }


    private final ArrayList<Integer> drinks = new ArrayList<>();
    private BillingStrategy strategy;

    public Customer(BillingStrategy strategy) {
        this.strategy = strategy;
    }

    public void add(int price, int quantity) {
        this.drinks.add(this.strategy.getActPrice(price * quantity));
    }

    public void printBill() {
        int sum = this.drinks.stream().mapToInt(v -> v).sum();
        System.out.println();
        this.drinks.stream().forEach(e -> System.out.print(" ;:" + e));
        this.drinks.clear();
    }

    public void setStrategy(BillingStrategy strategy) {
        this.strategy = strategy;
    }

}


@FunctionalInterface
interface BillingStrategy {

    int getActPrice(int rawPrice);

    static BillingStrategy normalStrategy() {
        return rawPrice -> rawPrice;
    }

    static BillingStrategy happyHourStrategy() {
        return rawPrice -> rawPrice / 2;
    }

    default BillingStrategy defaultStrategy() {
        return rawPrice -> rawPrice * 2;
    }

}