package com.erlong.proxy;

public class ProxyDemo {

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        OrderProxy proxy = new OrderProxy(orderService);
        orderService = (OrderService) proxy.getProxy();
        orderService.test1();
        orderService.test2();
    }
}
