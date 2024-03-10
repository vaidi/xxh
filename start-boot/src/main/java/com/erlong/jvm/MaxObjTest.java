package com.erlong.jvm;

import java.util.ArrayList;
import java.util.Random;

public class MaxObjTest {

    byte[] buffer = new byte[new Random().nextInt(1024 * 1024)];

    public static void main(String[] args) {
        ArrayList<MaxObjTest> list = new ArrayList<>();
        while (true) {
            list.add(new MaxObjTest());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
