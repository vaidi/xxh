package com.erlong.spi;


public class BlueService implements SpiService {
    @Override
    public void sayHello() {
        System.out.println("blue");
    }
}
