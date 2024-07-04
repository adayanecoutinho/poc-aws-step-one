package br.com.zup.poc_aws_step_one.domain.service;

import br.com.zup.poc_aws_step_one.domain.model.Message;

public interface MessageProcessorService {
    void processMessage(Message message);
}
