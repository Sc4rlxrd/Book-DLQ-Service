package com.scarlxrd.BookDlq.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDlqConfig {

    public static final String DLQ_NAME = "client.book.queue.dlq";

    @Bean
    public Queue dlq() {
        return new Queue(DLQ_NAME, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
