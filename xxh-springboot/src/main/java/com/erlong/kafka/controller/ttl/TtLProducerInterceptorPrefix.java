package com.erlong.kafka.controller.ttl;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * 生产者拦截器
 */
public class TtLProducerInterceptorPrefix implements ProducerInterceptor<String,String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        Long timestamp = record.timestamp();
        DelayQueue delayQueue = new DelayQueue();



        return null;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
