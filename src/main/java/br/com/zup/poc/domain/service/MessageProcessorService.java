package br.com.zup.poc.domain.service;

import br.com.zup.poc.domain.model.Message;

public interface MessageProcessorService {
    void processMessage(Message message);
}
