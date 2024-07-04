package br.com.zup.poc_aws_step_one.domain.service.impl;

import br.com.zup.poc_aws_step_one.domain.model.Message;
import br.com.zup.poc_aws_step_one.domain.service.MessageProcessorService;
import br.com.zup.poc_aws_step_one.infrastructure.dynamodb.DynamoDbRepository;
import br.com.zup.poc_aws_step_one.infrastructure.kafka.KafkaProducer;
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
        dynamoDbRepository.saveMessage(message);
        kafkaProducer.sendMessage(message);
    }
}
