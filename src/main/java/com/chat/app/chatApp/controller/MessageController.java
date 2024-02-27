package com.chat.app.chatApp.controller;

import com.chat.app.chatApp.entity.MessageRequest;
import com.chat.app.chatApp.service.MessageServiceImpl;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class MessageController {
    private MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send/message")
    public ResponseEntity<Authenticator.Success> sendMessage(@RequestBody MessageRequest messageRequest) {

        messageService.sendMessage(messageRequest.getSenderId(),
                messageRequest.getReceiverId(), messageRequest.getMessage());
        return new ResponseEntity<>(HttpStatus.CREATED);


    }
}
