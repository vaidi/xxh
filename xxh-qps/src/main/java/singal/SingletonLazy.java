package singal;

/**
 * 双重检查机制
 */
public class SingletonLazy {

    private static volatile SingletonLazy singletonLazy = null;
    private SingletonLazy(){}
    public static SingletonLazy getInstance(){
        if(singletonLazy==null){
            synchronized (SingletonLazy.class){
                if(singletonLazy ==null){
                    singletonLazy = new SingletonLazy();
                }
            }
        }
        return singletonLazy;
    }



}
