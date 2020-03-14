package singal;

public class SingletonHungary {
    private static SingletonHungary singletonHungary = new SingletonHungary();
    private SingletonHungary(){}
    public static SingletonHungary getInstance(){
        return singletonHungary;
    }
}
