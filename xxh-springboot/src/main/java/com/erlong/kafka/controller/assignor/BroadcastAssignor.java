package com.erlong.kafka.controller.assignor;

import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广播的分配策略
 */
public class BroadcastAssignor extends AbstractPartitionAssignor {

    /**
     * 拉取每个主题对应的消费者列表
     * @param consumerMetadata
     * @return
     */
    private Map<String,List<String>> consumersPerTopic(Map<String,Subscription> consumerMetadata){
        Map<String,List<String>> res = new HashMap<>();
        for(Map.Entry<String,Subscription> subscriptionEntry:consumerMetadata.entrySet()){
            String consumerId = subscriptionEntry.getKey();
            Subscription value = subscriptionEntry.getValue();
            List<String> topics = value.topics();
            for (String topic : topics) {
                put(res,topic,consumerId);
            }
        }
        return res;

    }

    @Override
    public Map<String, List<TopicPartition>> assign(Map<String, Integer> partitionsPerTopic, Map<String, Subscription> subscriptionMap) {
        Map<String, List<String>> consumersPerTopic = consumersPerTopic(subscriptionMap);
        Map<String,List<TopicPartition>> assignment = new HashMap<>();
        subscriptionMap.keySet().forEach(memberId->assignment.put(memberId,new ArrayList<>()));
        //针对每一个主题，为每一个订阅的消费者分配所有的分区
        consumersPerTopic.entrySet().forEach(topicEntry->{
            String topic = topicEntry.getKey();
            List<String> members = topicEntry.getValue();
            Integer numPartitionsForTopic = partitionsPerTopic.get(topic);
            if(numPartitionsForTopic == null || members.isEmpty()){
                return;
            }
            List<TopicPartition> partitions = AbstractPartitionAssignor.partitions(topic, numPartitionsForTopic);
            if(!partitions.isEmpty()){
                members.forEach(memberId->assignment.get(memberId).addAll(partitions));
            }
        });
        return assignment;
    }

    @Override
    public String name() {
        return "broadcast";
    }
}
