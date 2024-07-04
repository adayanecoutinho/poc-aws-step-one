package br.com.zup.poc_aws_step_one.application;

import br.com.zup.poc_aws_step_one.domain.model.Message;
import br.com.zup.poc_aws_step_one.domain.service.MessageProcessorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageHandler {

    private final MessageProcessorService messageProcessorService;

    public MessageHandler(MessageProcessorService messageProcessorService) {
        this.messageProcessorService = messageProcessorService;
    }

    public void handleMessage(String messageBody) {
        Message message = new Message();
        message.setContent(messageBody);
        message.setId(generateId());

        messageProcessorService.processMessage(message);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
