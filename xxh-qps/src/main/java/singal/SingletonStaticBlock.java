package singal;

/***
 *
 * 静态代码块
 */
public class SingletonStaticBlock {

    private static SingletonStaticBlock singletonStaticBlock = null;
    static {
        singletonStaticBlock = new SingletonStaticBlock();
    }
    private SingletonStaticBlock(){}
    public static SingletonStaticBlock getInstance(){
        return singletonStaticBlock;
    }

}
