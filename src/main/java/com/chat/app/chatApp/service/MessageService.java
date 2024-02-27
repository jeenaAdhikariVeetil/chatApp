package com.chat.app.chatApp.service;

import com.chat.app.chatApp.entity.MessageRequest;

public interface MessageService {
    public void sendMessage(MessageRequest messageRequest);
}
