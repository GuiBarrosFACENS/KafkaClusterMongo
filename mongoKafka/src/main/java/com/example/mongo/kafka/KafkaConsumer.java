package com.example.mongo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "ecommerce.cliente2", groupId = "ecommerci-groupId")
    public void consumer(String message){
        System.out.println(message);
    }

}
