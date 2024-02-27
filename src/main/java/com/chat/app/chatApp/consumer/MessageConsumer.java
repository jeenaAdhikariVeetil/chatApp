package com.chat.app.chatApp.consumer;

import com.chat.app.chatApp.constant.ChatConstants;
import com.chat.app.chatApp.entity.Message;
import com.chat.app.chatApp.entity.MessageRequest;
import com.chat.app.chatApp.entity.Users;
import com.chat.app.chatApp.exception.SelfMessageException;
import com.chat.app.chatApp.exception.UserNotFound;
import com.chat.app.chatApp.repository.MessageRepo;
import com.chat.app.chatApp.repository.UserRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private MessageRepo messageRepo;
    private UserRepo userRepo;

    public MessageConsumer(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }


    /**
     * @param messageRequest messageRequest
     * consumes the message sent by the user
     */
    @RabbitListener(queues = ChatConstants.QUEUE_NAME)
    public void consumeMessage(MessageRequest messageRequest) {
        Users sender = userRepo.findById(messageRequest.getSenderId()).
                orElseThrow(() -> new UserNotFound("Sender not found"));
        Users receiver = userRepo.findById(messageRequest.getReceiverId()).
                orElseThrow(() -> new UserNotFound("Receiver not found"));
        if (sender.equals(receiver)) {
            throw new SelfMessageException("Cannot send a message to yourself");
        }
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageRequest.getMessage());
        messageRepo.save(message);
    }
}
