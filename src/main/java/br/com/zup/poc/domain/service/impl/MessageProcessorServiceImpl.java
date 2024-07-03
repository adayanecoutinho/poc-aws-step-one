package br.com.zup.poc.domain.service.impl;

import br.com.zup.poc.domain.model.Message;
import br.com.zup.poc.domain.service.MessageProcessorService;
import br.com.zup.poc.infrastructure.dynamodb.DynamoDbRepository;
import br.com.zup.poc.infrastructure.kafka.KafkaProducer;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorServiceImpl implements MessageProcessorService {

    private final DynamoDbRepository dynamoDbRepository;
    private final KafkaProducer kafkaProducer;

    public MessageProcessorServiceImpl(DynamoDbRepository dynamoDbRepository, KafkaProducer kafkaProducer) {
        this.dynamoDbRepository = dynamoDbRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void processMessage(Message message) {
        // Gravar no DynamoDB
        dynamoDbRepository.saveMessage(message);

        // Publicar no Kafka
        kafkaProducer.sendMessage(message);
    }
}
