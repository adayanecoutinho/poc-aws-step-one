package br.com.zup.poc_aws_step_one.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    /*
    @KafkaListener(topics = "topicTeste", groupId = "group_teste")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }*/
}
