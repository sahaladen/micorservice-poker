package com.example.poker.service;

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
    private String queueName;

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
    public void publishCardTest(){
        String routingKey = "showcards.complete";

        log.info("sending info of all cards... to {}", exchangeName);
        //create Card object
        Card card = new Card(Suit.SPADES, Rank.ACE);
        amqpTemplate.convertAndSend(exchangeName,routingKey,card);
    }
    public void showAllCards(){

    }
}
