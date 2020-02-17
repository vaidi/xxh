package ali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main1 {

    public static void main(String[] args) {
        int[] array = {1,4,9};
        List list =new ArrayList();
        list.add(1);
        list.add(2);
        list.add(5);

        System.out.println("size:"+list.size());
        List list1 = list.subList(1,list.size());
        list1.stream().forEach(System.out::println);








        //System.out.println(new A());
    }

}
class A {
    public A() {
        new B();
    }
}

class B {
    public B() {
        new A();
    }
}