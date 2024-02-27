package com.chat.app.chatApp.service;

import com.chat.app.chatApp.constant.ChatConstants;
import com.chat.app.chatApp.entity.Message;
import com.chat.app.chatApp.entity.MessageRequest;
import com.chat.app.chatApp.repository.MessageRepo;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private RabbitTemplate rabbitTemplate;
    private MessageRepo messageRepo;

    public MessageServiceImpl(RabbitTemplate rabbitTemplate, MessageRepo messageRepo) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageRepo = messageRepo;
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

    @Override
    public List<Message> viewReceivedMessages(Long receiverId) {
        return messageRepo.findByReceiverId(receiverId);
    }

    @Override
    public List<Message> viewSentMessages(Long senderId) {
        return messageRepo.findBySenderId(senderId);
    }

    @Override
    public List<Message> viewReceivedForUser(Long senderId, Long receiverId) {
        return messageRepo.findBySenderIdAndReceiverId(senderId,receiverId);
    }
}
