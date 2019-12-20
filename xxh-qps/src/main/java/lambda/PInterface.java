package lambda;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 15:06
 * @Description: h自测用来的函数式接口
 */
//@PFunctionalInterface
@FunctionalInterface
public interface PInterface {

    //抽象方法  只能拥有一个抽象方法
    int sub();

    // java.lang.Object中的public方法
    @Override
    boolean equals(Object var1);

    //默认方法
    default void defaultMethod() {

    }

    //静态方法
    static void staticMethod() {

    }


}
