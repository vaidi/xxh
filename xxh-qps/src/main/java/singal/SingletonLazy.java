package singal;

/**
 * 双重检查机制
 *
 * w为什么要用双重检查机制
 *1，创建对象是多条字节码命令，不是原子性
 * 2、指令重拍 会造成singleObj已经不是null但是还没有初始化完成。
 *
 */
public class SingletonLazy {

    private static volatile SingletonLazy singletonLazy = null;
    private SingletonLazy(){}
    public static SingletonLazy getInstance(){
        if(singletonLazy==null){
            //线程2 走到第一个判断null后
            synchronized (SingletonLazy.class){
                if(singletonLazy ==null){
                    singletonLazy = new SingletonLazy(); //part 3n

                }
            }
        }
        return singletonLazy;
    }

    /**
     * 锁加到方法上浪费资源，影响性能
     * @return
     */
    public static synchronized SingletonLazy getSingleObj(){
        if(singletonLazy == null){
            return new SingletonLazy();
        }
        return singletonLazy;
    }


    public static SingletonLazy instanceOne(){
        if(singletonLazy == null){
            synchronized (SingletonLazy.class){
                singletonLazy = new SingletonLazy();
                //线程1在这里已经不是null单是还没有初始化完成

            }
        }
        return singletonLazy;
    }




}
