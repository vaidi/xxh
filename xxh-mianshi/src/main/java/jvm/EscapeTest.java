package jvm;

import java.util.stream.IntStream;

public class EscapeTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        IntStream.range(0,5_000_000).forEach(e->{
           // createObject();
            createSyn();
        });
        System.out.println("耗时:"+(System.currentTimeMillis()-startTime)+"ms");
    }

    private static Object createObject() {
        return new Object();
    }
    private static void createSyn() {
        synchronized (new Object()){

        }
    }

    public static Object globalVariableObject;

    public Object instanceObject;
    public void globalVariableEscape(){
        globalVariableObject = new Object(); //静态变量,外部线程可见,发生逃逸
    }

    public void instanceObjectEscape(){
        instanceObject = new Object(); //赋值给堆中实例字段,外部线程可见,发生逃逸
    }

    public Object returnObjectEscape(){
        return new Object();  //返回实例,外部线程可见，发生逃逸
    }

    public void noEscape(){
        synchronized (new Object()){
            //仅创建线程可见,对象无逃逸
        }
        Object noEscape = new Object();  //仅创建线程可见,对象无逃逸
    }
}
