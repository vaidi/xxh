package com.erlong.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ProducerController {

    @Autowired
    KafkaTemplate<String,String> kafka;

    AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping("/kafka")
    public String data(String message){
        kafka.send("first",message);
        return "ok"+atomicInteger.incrementAndGet();
    }

}
