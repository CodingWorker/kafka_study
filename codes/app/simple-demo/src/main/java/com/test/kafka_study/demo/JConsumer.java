package com.test.kafka_study.demo;

import kafka.consumer.*;
import kafka.javaapi.consumer.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA
 * Project: my-kafka-app
 * User: DaiYan
 * Date: 2017/10/23
 */
public class JConsumer extends Thread{
        private kafka.javaapi.consumer.ConsumerConnector consumer;
        private String topic;
        private final int SLEEP = 1000 * 3;

        public JConsumer(String topic) {
            consumer = Consumer.createJavaConsumerConnector(this.consumerConfig());
            this.topic = topic;
        }

        private ConsumerConfig consumerConfig() {
            Properties props = new Properties();
            props.put("zookeeper.connect", ConfigAPI.KafkaProperties.ZK);
            props.put("group.id", ConfigAPI.KafkaProperties.GROUP_ID);
            props.put("zookeeper.session.timeout.ms", "40000");
            props.put("zookeeper.sync.time.ms", "200");
            props.put("auto.commit.interval.ms", "1000");
            return new ConsumerConfig(props);
        }

        @Override
        public void run() {
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            topicCountMap.put(topic, new Integer(1));
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
            KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                System.out.println("Receive->[" + new String(it.next().message()) + "]");
                try {
                    sleep(SLEEP);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
}
