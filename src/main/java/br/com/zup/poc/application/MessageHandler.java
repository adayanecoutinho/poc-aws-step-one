package br.com.zup.poc.application;

import br.com.zup.poc.domain.model.Message;
import br.com.zup.poc.domain.service.MessageProcessorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageHandler {

    private final MessageProcessorService messageProcessorService;

    public MessageHandler(MessageProcessorService messageProcessorService) {
        this.messageProcessorService = messageProcessorService;
    }

    public void handleMessage(String messageBody) {
        // Transformar o corpo da mensagem em um objeto Message
        Message message = new Message();
        message.setContent(messageBody);
        // Aqui você pode adicionar um ID ou outras propriedades necessárias
        message.setId(generateId());

        // Processar a mensagem
        messageProcessorService.processMessage(message);
    }

    private String generateId() {
        // Implementar a lógica de geração de ID
        return UUID.randomUUID().toString();
    }
}
