package com.test.kafka_study;

import kafka.producer.KeyedMessage;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/10/28
 * Project: kafka-app
 */
public class ProducerTest {
    public static void main(String[] args) {
        long events = Long.parseLong(args[0]);
        Random rnd = new Random();
        Properties props = new Properties();
        props.put("metadata.broker.list", "192.168.2.105:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder"); //默认字符串编码消息
        props.put("partitioner.class", "example.producer.SimplePartitioner");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new ProducerTest<String, String>(config);

        for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2."+rnd.nextInt(255);
            String msg = runtime + ",www.example.com,"+ip;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
            producer.send(data);
        }
        producer.close();
    }

}
