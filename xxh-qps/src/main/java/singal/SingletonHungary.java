package singal;

/***
 * 饿汉模式
 */
public class SingletonHungary {
    private static SingletonHungary singletonHungary = new SingletonHungary();
    private SingletonHungary(){}
    public static SingletonHungary getInstance(){
        return singletonHungary;
    }
}
