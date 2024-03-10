package com.erlong.kafka.controller.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class FirstMultiConsumerThreadDemo {

    public static final String breokerList = "loaclhost:9092";

    public static final String topic ="topci-demo";

    public static final String groupId = "group.demo";

    public static Properties initConfig(){
        Properties props = new Properties();
        // key.deserializer：key的反序列化器
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value.deserializer：value的反序列化器
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // fetch.max.bytes：一次拉取的最小可返回数据量：1Byte
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 100 * 1024);
        // fetch.max.bytes：一次拉取的最大数据量：50M
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 50 * 1024 * 1024);
        // fetch.max.wait.ms：一次拉取的最大等待时间：500ms
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
        // max.poll.records: 一次拉取的最大条数
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 2000);
        // max.partition.fetch.bytes：一次拉取时，每个分区最大拉取数据量，默认1M
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 1 * 1024 * 1024);
        // auto.offset.reset：当 Kafka 中没有初始偏移量或当前偏移量在服务器中不存在 （如，数据被删除了）时，自动设置开始消费的偏移量位置，默认latest。
        // earliest：自动重置偏移量到最早的偏移量（从头开始消费）。
        // latest：默认，自动重置偏移量为最新的偏移量（从最新的接收到的数据开始消费）。
        // none：如果消费组原来的（previous）偏移量不存在，则向消费者抛异常。 anything：向消费者抛异常。
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // enable.auto.commit：是否允许自动提交offset，默认是。
      //  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // auto.commit.interval.ms：自动提交offset的时间间隔，默认5秒。
     //   props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 5000);

        // heartbeat.interval.ms：消费者心跳检测时间间隔，默认3秒。
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
        // session.timeout.ms：session过期时间，默认10秒。
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        // max.poll.interval.ms：一批次数据最大可以执行时间，默认5分钟。
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000);
        // partition.assignment.strategy：分区分配策略，默认5分钟。
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RangeAssignor.class.getName());
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        int consumerThreadNum = 4;
        for(int i =0; i < consumerThreadNum ;i++){

        }
    }
    public static class KakaConsumerThread extends Thread{

        private KafkaConsumer<String,String> kafkaConsumer;

        public KakaConsumerThread(Properties  properties,String topic){
            this.kafkaConsumer = new KafkaConsumer<String, String>(properties);
            this.kafkaConsumer.subscribe(Arrays.asList(topic));

        }

        @Override
        public void run(){
            try {
                while (true){
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        //处理消息模块


                    }




                }



            }catch (Exception e){

            }finally {
                kafkaConsumer.close();
            }



        }



    }




}
