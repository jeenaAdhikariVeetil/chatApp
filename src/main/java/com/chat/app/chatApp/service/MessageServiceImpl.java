package com.chat.app.chatApp.service;

import com.chat.app.chatApp.constant.ChatConstants;
import com.chat.app.chatApp.entity.MessageRequest;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private RabbitTemplate rabbitTemplate;

    public MessageServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(MessageRequest messageRequest) {
        try {
            rabbitTemplate.convertAndSend(ChatConstants.TOPIC_NAME, ChatConstants.ROUTING_KEY,
                    messageRequest);
        } catch (AmqpException ex) {
            throw new AmqpException(ChatConstants.AMQP_ERROR);
        }

    }
}
