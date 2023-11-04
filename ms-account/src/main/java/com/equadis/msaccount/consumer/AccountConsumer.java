package com.equadis.msaccount.consumer;

import com.equadis.msaccount.dtos.CustomerRecordDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AccountConsumer {

    @RabbitListener(queues = "${broker.queue.account.customer}" )
    public void listenAccountQueue(@Payload CustomerRecordDto dto) {
        System.out.println(dto);
    }

}
