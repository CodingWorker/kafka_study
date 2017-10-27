package com.test.kafka_study;

/**
 * Created by IntelliJ IDEA
 * Project: my-kafka-app
 * User: DaiYan
 * Date: 2017/10/23
 */
public class ConfigAPI {
        public interface KafkaProperties {
            String ZK = "10.211.55.15:2181,10.211.55.17:2181,10.211.55.18:2181";
            String GROUP_ID = "test_group1";
            String TOPIC = "test2";
            String BROKER_LIST = "10.211.55.15:9092,10.211.55.17:9092,10.211.55.18:9092";
            int BUFFER_SIZE = 64 * 1024;
            int TIMEOUT = 20000;
            int INTERVAL = 10000;
        }
}
