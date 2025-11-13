package com.example.poker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;


@Configuration
public class AmqpConfiguration {

    @Bean
    public TopicExchange topicExchange(@Value("${amqp.exchange.name}") final String exchangeName){
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)
                .build();
    }
    @Bean
    public Queue pokerQueue() {
        return new Queue("playerbalance.complete", true, false, false); // durable queue
    }

    @Bean
    public Binding binding(Queue pokerQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(pokerQueue)
                .to(topicExchange)
                .with("playerbalance.complete"); // routing key
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory, TopicExchange exchange, Queue queue, Binding binding) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        // Explicitly declare all on RabbitMQ
        admin.declareExchange(exchange);
        admin.declareQueue(queue);
        admin.declareBinding(binding);
        return admin;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
