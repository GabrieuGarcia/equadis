package com.equadis.msaccount.producer;

import com.equadis.msaccount.dtos.AccountingMessageDto;
import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public Customer publishMessageCustomerValidation(Account model) {

        var message = new AccountingMessageDto();
        message.setCustomerId(model.getCustomerId());

        rabbitTemplate.setReceiveTimeout(100000L);

        var response = rabbitTemplate.convertSendAndReceive("", routingKey, message);

        ObjectMapper objectMapper = new ObjectMapper();
        Customer cust = objectMapper.convertValue(response, Customer.class);

        return cust;
    }

}
