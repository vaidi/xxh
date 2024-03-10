package com.erlong.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceMain {
    public static void main(String[] args) {

        ServiceLoader<SpiService> spiLoader = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iteratorSpi = spiLoader.iterator();
        while (iteratorSpi.hasNext()) {
            SpiService demoService = iteratorSpi.next();
            demoService.sayHello();
        }

    }

}
