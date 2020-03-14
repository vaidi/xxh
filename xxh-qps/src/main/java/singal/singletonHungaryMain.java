package singal;

public class singletonHungaryMain {

    public static void main(String[] args) {
       Integer code =  SingletonHungary.getInstance().hashCode();
       Integer code1 =  SingletonHungary.getInstance().hashCode();
        System.out.println("code:"+code+",code1:"+code1);




    }

}
