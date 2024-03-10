package jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\jvm\oom.dump -Xms20m -Xmx20m
 * -XX:+PrintGC -XX:+PrintGCDetails -XX:NewRatio=3 -XX:SurvivorRatio=8
 * -XX:MaxMetaspaceSize=10m -Xss2k -XX:+PrintGCDateStamps -XX:+PrintCommandLineFlags
 */
public class JvmHeapDemo {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        List<Integer> arry = new ArrayList<>();
        IntStream.range(0,1999).forEach(e->{
            arry.add(_1MB);
        });
    }


    public static void testStaicOverFlow(){
        testStaicOverFlow();
    }


}
