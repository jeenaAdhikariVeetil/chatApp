package com.chat.app.chatApp.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MessageConfig {
    @Value("${rabbitmq.queue.name}")
    String queueName;
    @Value("${rabbitmq.exchange.name}")
    String exchange;
    @Value("${rabbitmq.routingKey.name}")
    String routingKey;

    /**
     * @return Spring bean for rabbitMq queue
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    /**
     * @return Spring bean for rabbitMq exchange
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    /**
     * @param queue queue
     * @param exchange exchange
     * @return Binding between exchange and queue using routing Key
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).
                to(exchange).with(routingKey);
    }

    /**
     * @return returns messageConverter
     */
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * @param connectionFcatory connectionFcatory
     * @return returns a rabbitMq template
     */
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFcatory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFcatory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
