package com.erlong.kafka.controller.assignor;

import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.Map;

public class RandomAssignor extends AbstractPartitionAssignor {


    @Override
    public Map<String, List<TopicPartition>> assign(Map<String, Integer> partitionsPerTopic, Map<String, Subscription> subscriptions) {
        return null;
    }

    @Override
    public String name() {
        return "random";
    }
}
