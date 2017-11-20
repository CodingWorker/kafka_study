package com.test.kafka_study;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DaiYan on 2017/11/19.
 */
public class ProducerDemo {
    public static void main(String[] args) {
        Map<String,Object> configs=new HashMap<String,Object>();
        Producer producer=new KafkaProducer(configs);
        String topic="test";
        ProducerRecord producerRecord=new ProducerRecord(topic,1,"msg","hello");
        producer.send(producerRecord);
    }
}
