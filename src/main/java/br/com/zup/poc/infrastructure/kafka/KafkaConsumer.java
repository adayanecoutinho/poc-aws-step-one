package br.com.zup.poc.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "topicTeste", groupId = "group_teste")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
