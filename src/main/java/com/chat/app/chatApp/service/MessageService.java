package com.chat.app.chatApp.service;

import com.chat.app.chatApp.entity.Message;
import com.chat.app.chatApp.entity.MessageRequest;

import java.util.List;

public interface MessageService {
    public void sendMessage(MessageRequest messageRequest);
    public List<Message> viewReceivedMessages(Long receiverId);
    public List<Message> viewSentMessages(Long senderId);
    public List<Message> viewReceivedForUser(Long senderId,Long receiverId);
}
