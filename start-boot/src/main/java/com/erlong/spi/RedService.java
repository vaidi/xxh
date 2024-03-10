package com.erlong.spi;

public class RedService implements SpiService {
    @Override
    public void sayHello() {
        System.out.println("red");
    }
}
