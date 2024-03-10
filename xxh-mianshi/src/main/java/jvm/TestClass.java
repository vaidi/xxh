package jvm;

public class TestClass {

    public static void main(String[] args) {
        System.out.println("inside main");
        new Dog();
        System.out.println("after creating Dog");
        try {
            Class cat=Class.forName("jvm.Cat");
            Class cat2=Class.forName("jvm.Cat");
            Cat catObj = new Cat();
            System.err.println(cat == cat2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Couldn't find Cat");
        }
        System.out.println("finish main");
    }

}
class Dog{
    static {
        System.out.println("i am is dog");
    }
}
class Cat{
    static {
        System.out.println("i am is cat");
    }

}