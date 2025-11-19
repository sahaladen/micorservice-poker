package com.example.poker.service;

import com.example.poker.BetManager;
import com.example.poker.Card;
import com.example.poker.Rank;
import com.example.poker.Suit;
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
        String routingKey = "playerbalance.complete";

        log.info("sending info of all cards... to {}", exchangeName);
        amqpTemplate.convertAndSend(exchangeName,routingKey,"it works");
    }

    public void publishPlayerBalance(){
        String routingKey = "playerbalance.complete";

        log.info("sending balance over... to {}", exchangeName);

        BetManager betManager = new BetManager(9999,9999);

        long playerBalance =42;

        log.info("playerbalance {}: ", playerBalance);
        amqpTemplate.convertAndSend(exchangeName,routingKey,playerBalance);
    }

}
