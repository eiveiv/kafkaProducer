package com.dev.demo.endpoint;

import com.dev.demo.model.GoalUpdate;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaEndpoint {

    @Value("${broker.server}")
    private String servers;

    @Value("${kafka.serializer}")
    private String serializer;

    public void sendGoalMessage(GoalUpdate goalUpdate) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("key.serializer", serializer);
        properties.put("value.serializer", serializer);

        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(properties);

        myProducer.send(new ProducerRecord<>("goal_topic", goalUpdate.toString()));
        myProducer.close();
    }
}
