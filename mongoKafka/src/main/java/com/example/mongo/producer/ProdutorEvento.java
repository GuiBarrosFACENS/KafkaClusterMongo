package com.example.mongo.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.beans.XMLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Slf4j
public class ProdutorEvento {

    private final Producer<String, String> producer;

    public ProdutorEvento(){
        producer = criarProducer();
    }

    private Producer<String, String> criarProducer(){
        if (producer != null){
            return producer;
        }

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localgost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("serializer.class", "kafkka.serializer.DefaultEncoder");

        return null;

    }

    public void executar(){
        String chave = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String mesagem = sdf.format(new Date());
        mesagem += " | " + chave;
        mesagem += "|NOVA MENSAGEM";

        log.info("Iniciando envio da mensagem");
        ProducerRecord<String, String> record = new ProducerRecord<String,String>("topioc", chave, mesagem);
        producer.send(record);
        producer.flush();
        producer.close();
        log.info("Mensagem enviada com sucesso [{}]", mesagem);
    }

}
