package br.com.zup.poc.infrastructure.kafka;

import br.com.zup.poc.domain.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) {
        log.info("Sending message to Kafka " + message.getContent());
        kafkaTemplate.send("topicTeste", message.getContent());

        log.info("Sended message to Kafka " + message);

    }
}
