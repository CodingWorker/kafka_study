package com.test.kafka_study;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.omg.CORBA.Object;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DaiYan on 2017/11/19.
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Map<String,Object> configs=new HashMap<String,Object>();
        Consumer consumer=new KafkaConsumer(configs);
        ConsumerRecords<String,String> consumerRecords= consumer.poll(10);
        for(ConsumerRecord record:consumerRecords){
            System.out.println(record.key()+" ==> "+record.value());
        }
    }
}
