package com.chat.app.chatApp.service;

public interface MessageService {
    public void sendMessage(Long senderId,Long reciverId,
                            String message);
}
