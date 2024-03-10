package com.erlong.jvm.heapoom;

public class GCLockerTest {
    static final int ITERS = 100;
    static final int ARR_SIZE =  10000;
    static final int WINDOW = 10000000;

    static native void acquire(int[] arr);
    static native void release(int[] arr);

    static final Object[] window = new Object[WINDOW];

    public static void main(String... args) throws Throwable {
        System.loadLibrary("com.erlong.jvm.heapoom.GCLockerTest");
        int[] arr = new int[ARR_SIZE];
        for (int i = 0; i < ITERS; i++) {
            acquire(arr);
            System.out.println("Acquired");
            try {
                for (int c = 0; c < WINDOW; c++) {
                    window[c] = new Object();
                }
            } catch (Throwable t) {
                // omit
            } finally {
                System.out.println("Releasing");
                release(arr);
            }
        }
    }


}
