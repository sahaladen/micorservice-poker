package com.example.poker.config;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    @Bean
    public TopicExchange topicExchange(@Value("${amqp.exchange.name}") final String exchangeName){
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)
                .build();
    }


}
