package lambda;

/**
 * 验证lambda不是一个内部类
 */
public class HelloLambda {
    Runnable r1 = () -> { System.out.println(this); };
    Runnable r2 = () -> { System.out.println(toString()); };
    Runnable r3 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };
    public static void main(String[] args) {
        new HelloLambda().r1.run();
        new HelloLambda().r2.run();
        new HelloLambda().r3.run();
    }
    @Override
    public String toString() { return "Hello Hoolee"; }


}
