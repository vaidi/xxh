package lambda.chap.chap0;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class Shop {


    private static final Random RANDOM = new Random();



//    public Stream<CompletableFuture<String>> findPricesStream(String product) {
//        return shops.stream()
//                .map(shop -> CompletableFuture.supplyAsync(
//                        () -> shop.getPrice(product), executor))
//                .map(future -> future.thenApply(Quote::parse))
//                .map(future -> future.thenCompose(quote ->
//                        CompletableFuture.supplyAsync(
//                                () -> Discount.applyDiscount(quote), executor)));
//    }


    public Future<Double> getPriceAsync1(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()->{
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }






    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
