package jvm;

public class HeapDemo {
    public static void main(String[] args) {

        int num = Runtime.getRuntime().availableProcessors();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long initMemory = Runtime.getRuntime().totalMemory();
        System.out.println("initMemort:"+initMemory+"m"+"freeMemory:"+freeMemory+",available:"+num);
        System.out.println("MaxMemort:"+maxMemory+"m");

        System.out.println("系统内存大小"+initMemory*64.0/1024+"G");
        System.out.println("系统内存大小"+maxMemory*4.0/1024+"G");

    }

}
