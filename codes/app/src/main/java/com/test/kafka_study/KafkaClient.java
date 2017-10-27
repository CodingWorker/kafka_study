package com.test.kafka_study;

/**
 * Created by IntelliJ IDEA
 * Project: my-kafka-app
 * User: DaiYan
 * Date: 2017/10/23
 */
public class KafkaClient {
        public static void main(String[] args) {
            JProducer pro = new JProducer(ConfigAPI.KafkaProperties.TOPIC);
            pro.start();

            JConsumer con = new JConsumer(ConfigAPI.KafkaProperties.TOPIC);
            con.start();
        }
}
