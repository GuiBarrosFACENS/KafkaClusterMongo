package com.example.mongo.controller;

import com.example.mongo.producer.ProdutorEvento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/producer/{message}")
    public void producer(@PathVariable("message") String message){
        this.kafkaTemplate.send("ecommerce.cliente2", message);
    }

    @GetMapping("/producer")
    public void producer2(){
        log.info("Iniciando aplicação");
        ProdutorEvento produtor = new ProdutorEvento();
        produtor.executar();
    }



}
