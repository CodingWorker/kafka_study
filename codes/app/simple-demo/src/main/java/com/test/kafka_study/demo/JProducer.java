package com.test.kafka_study.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA
 * Project: my-kafka-app
 * User: DaiYan
 * Date: 2017/10/23
 */
public class JProducer extends Thread {

    private Producer<Integer, String> producer;
    private String topic;
    private Properties props = new Properties();
    private final int SLEEP = 1000 * 3;

    public JProducer(String topic) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "10.211.55.18:9092");
        producer = new KafkaProducer<Integer, String>(props);
        this.topic = topic;
    }

    @Override
    public void run() {
        int offsetNo = 1;
        while (true) {
            String msg = new String("Message_" + offsetNo);
            System.out.println("Send->[" + msg + "]");
            producer.send(new ProducerRecord<Integer, String>(topic, msg));
            offsetNo++;
            try {
                sleep(SLEEP);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
