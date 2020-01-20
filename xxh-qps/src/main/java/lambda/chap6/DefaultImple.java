package lambda.chap6;

public class DefaultImple  implements DefaultIn{


    @Override
    public void say() {

    }

    @Override
    public void self() {
        System.out.println("儿子要用爸爸的钱财生活");
    }

    public static void main(String[] args) {
        DefaultIn defaultIn = new DefaultImple();
        defaultIn.self();
        System.out.println(".....................");
        Thread.dumpStack();

    }


}

interface DefaultIn {

    void say();

    default void self(){
        System.out.println("默认是可以独自生活的！！！");
    }



}