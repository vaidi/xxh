package design.visitor;

/**
 * @Auther: YUANERL
 * @Date: 2019/12/19 15:46
 * @Description:
 * 被访问的元素里面有一个方法是用来接受访问者的
 *
 */
public interface Element {

    abstract void accept(PVector vector);
}
