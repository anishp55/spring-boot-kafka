package com.demo.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@EnableBinding(Source.class)
public class Producer {

    private final Source source;

    public Producer(Source source) {
        this.source = source;
    }

//    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
//    private static final String TOPIC = "users";
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    public void sendMessage(String message) {
//        logger.info(String.format("#### -> Producing message -> %s", message));
//        this.kafkaTemplate.send(TOPIC, message);
//    }


    public Source getSource() {
        return source;
    }
}
