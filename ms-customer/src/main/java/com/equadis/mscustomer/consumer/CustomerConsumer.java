package com.equadis.mscustomer.consumer;

import com.equadis.mscustomer.dto.AccountingMessageDto;
import com.equadis.mscustomer.dto.CustomerRecordDto;
import com.equadis.mscustomer.service.CustomerService;
import com.equadis.mscustomer.utils.CustomerConverter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {

    private final CustomerService customerService;

    public CustomerConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RabbitListener(queues = "${broker.queue.account.customer}")
    public Object listenAccountQueue(@Payload AccountingMessageDto dto) {
       return this.customerService.findById(dto.getCustomerId());
    }

}
