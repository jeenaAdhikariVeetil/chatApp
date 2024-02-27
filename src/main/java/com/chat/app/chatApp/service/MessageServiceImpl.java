package com.chat.app.chatApp.service;

import com.chat.app.chatApp.entity.Message;
import com.chat.app.chatApp.entity.Users;
import com.chat.app.chatApp.exception.SelfMessageException;
import com.chat.app.chatApp.exception.UserNotFound;
import com.chat.app.chatApp.repository.MessageRepo;
import com.chat.app.chatApp.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepo messageRepo;
    private UserRepo userRepo;

    public MessageServiceImpl(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void sendMessage(Long senderId, Long receiverId, String content) {
        Users sender = userRepo.findById(senderId).
                orElseThrow(() -> new UserNotFound("Sender not found"));
        Users receiver = userRepo.findById(receiverId).
                orElseThrow(() -> new UserNotFound("Receiver not found"));
        if (sender.equals(receiver)) {
            throw new SelfMessageException("Cannot send a message to yourself");
        }
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        messageRepo.save(message);
    }
}
