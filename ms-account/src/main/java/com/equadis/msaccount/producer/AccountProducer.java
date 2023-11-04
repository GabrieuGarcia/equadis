package com.equadis.msaccount.producer;

import com.equadis.msaccount.dtos.AccountingMessageDto;
import com.equadis.msaccount.model.Account;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProducer {

    final RabbitTemplate rabbitTemplate;

    public AccountProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.account.customer}")
    private String routingKey;

    public Object publishMessageCustomerValidation(Account model) {
        var message = new AccountingMessageDto();
        message.setCustomerId(model.getCustomerId());

        rabbitTemplate.setReceiveTimeout(60000L);

        return rabbitTemplate.convertSendAndReceive("", routingKey, message);
    }

}
