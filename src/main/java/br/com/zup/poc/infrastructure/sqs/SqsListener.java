package br.com.zup.poc.infrastructure.sqs;

import br.com.zup.poc.application.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Component
@Slf4j
public class SqsListener {
    private final SqsClient sqsClient;
    private final MessageHandler messageHandler;


        public SqsListener(SqsClient sqsClient, MessageHandler messageHandler) {
        this.sqsClient = sqsClient;
        this.messageHandler = messageHandler;
    }

    @Scheduled(fixedRate = 5000)
    public void startListening() {

        log.info("Listening for messages...");
        while (true) {
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl("https://localhost.localstack.cloud:4566/000000000000/FIlaTeste")
                    .maxNumberOfMessages(1)
                    .build();

            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

            for (Message message : messages) {
                messageHandler.handleMessage(message.body());
                sqsClient.deleteMessage(builder ->
                        builder.queueUrl("https://localhost.localstack.cloud:4566/000000000000/FIlaTeste").
                        receiptHandle(message.receiptHandle()));
            }
        }
    }
}
