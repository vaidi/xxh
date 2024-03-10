package com.erlong.kafka.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {


    @KafkaListener(topics = {"first"})
    public void consumerTopic(String msg){
        System.out.println("接收到kafka消息:"+msg);
    }

}
