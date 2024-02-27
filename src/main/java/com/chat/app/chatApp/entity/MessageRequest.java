package com.chat.app.chatApp.entity;

import lombok.Data;

@Data
public class MessageRequest {
    private Long senderId;
    private Long receiverId;
    private String message;
}
