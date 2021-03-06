package com.test.kafka_study;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
/**
 * Created by IntelliJ IDEA
 * Project: Demo
 * User: DaiYan
 * Date: 2017/10/23
 */
public class KafkaConsumer {
        private final ConsumerConnector consumer;
        private KafkaConsumer() {
            Properties props = new Properties();
            // zookeeper 配置
            props.put("zookeeper.connect", "localhost:2181");

            // group 代表一个消费组
            props.put("group.id", "lingroup");

            // zk连接超时
            props.put("zookeeper.session.timeout.ms", "5000");
            props.put("zookeeper.connection.timeout.ms", "10000");
            props.put("rebalance.backoff.ms", "2000");
            props.put("rebalance.max.retries", "10");
            props.put("zookeeper.sync.time.ms", "200");
            props.put("rebalance.max.retries", "5");
            props.put("rebalance.backoff.ms", "1200");


            props.put("auto.commit.interval.ms", "1000");
            props.put("auto.offset.reset", "smallest");
            // 序列化类
            props.put("serializer.class", "kafka.serializer.StringEncoder");

            ConsumerConfig config = new ConsumerConfig(props);
            consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        }

        void consume() {
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            topicCountMap.put(KafkaProducer.TOPIC, new Integer(1));

            StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
            StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

            Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
            KafkaStream<String, String> stream = consumerMap.get(KafkaProducer.TOPIC).get(0);
            ConsumerIterator<String, String> it = stream.iterator();
            while (it.hasNext())
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + it.next().message() + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }

        public static void main(String[] args) {
            new KafkaConsumer().consume();
        }
}
