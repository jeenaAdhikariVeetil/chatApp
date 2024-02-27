package com.chat.app.chatApp.controller;

import com.chat.app.chatApp.entity.Message;
import com.chat.app.chatApp.entity.MessageRequest;
import com.chat.app.chatApp.service.MessageServiceImpl;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessageController {
    private MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<Authenticator.Success> sendMessage(@RequestBody MessageRequest messageRequest) {
        messageService.sendMessage(messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/message/receiver/{receiverId}")
    public ResponseEntity<List<Message>> viewReceivedMessages(@PathVariable Long receiverId) {
        List<Message> messages = messageService.viewReceivedMessages(receiverId);
        return new ResponseEntity<List<Message>>(messages,HttpStatus.OK);
    }
    @GetMapping("/message/sender/{senderId}")
    public ResponseEntity<List<Message>> viewSentMessages(@PathVariable Long senderId) {
        List<Message> messages = messageService.viewSentMessages(senderId);
        return new ResponseEntity<List<Message>>(messages,HttpStatus.OK);
    }
    @GetMapping("/message/receiver/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> viewReceivedForUser(@PathVariable Long senderId,
                                                          @PathVariable Long receiverId) {
        List<Message> messages = messageService.viewReceivedForUser(senderId,receiverId);
        return new ResponseEntity<List<Message>>(messages,HttpStatus.OK);
    }
}
