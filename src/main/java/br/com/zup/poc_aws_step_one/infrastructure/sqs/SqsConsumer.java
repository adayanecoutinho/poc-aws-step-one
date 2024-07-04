package br.com.zup.poc_aws_step_one.infrastructure.sqs;

import br.com.zup.poc_aws_step_one.application.MessageHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
@Slf4j
@Getter
public class SqsConsumer {
    private final MessageHandler messageHandler;

    @Value("${aws.sqs.queue}")
    private final String queueName;

    public SqsConsumer(MessageHandler messageHandler, @Value("${aws.sqs.queue}") String queueName) {
        this.messageHandler = messageHandler;
        this.queueName = queueName;
    }

    @SqsListener(value = "${aws.sqs.queue}")
    public void startListening(String message) {
        log.info("Listening for messages...");
        messageHandler.handleMessage(message);
    }

}
