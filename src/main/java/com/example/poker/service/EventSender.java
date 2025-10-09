package com.example.poker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class EventSender {
    private final String exchangeName;
    private final AmqpTemplate amqpTemplate;

    public EventSender
            (@Value("${amqp.exchange.name}") final String exchangeName, AmqpTemplate amqpTemplate)
    {
        this.exchangeName = exchangeName;
        this.amqpTemplate = amqpTemplate;
    }

    public void publishTest(){
        String routingKey = "showcards.complete";

        log.info("sending info of all cards... to {}", exchangeName);
        amqpTemplate.convertAndSend(exchangeName,routingKey,"it works");
    }
}
