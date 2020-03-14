package singal;

/**
 * 内部类创建单例
 */
public class SingletonStaticInner {
    private SingletonStaticInner(){}
    private static class SingletonInner{
        private static SingletonStaticInner staticInner = new SingletonStaticInner();
    }
    public static SingletonStaticInner getInstance(){
        return SingletonInner.staticInner;
    }
}
