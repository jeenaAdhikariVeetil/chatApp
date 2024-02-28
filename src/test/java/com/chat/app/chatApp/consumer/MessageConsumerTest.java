package com.chat.app.chatApp.consumer;

import com.chat.app.chatApp.entity.Message;
import com.chat.app.chatApp.entity.MessageRequest;
import com.chat.app.chatApp.entity.Users;
import com.chat.app.chatApp.exception.SelfMessageException;
import com.chat.app.chatApp.exception.UserNotFound;
import com.chat.app.chatApp.repository.MessageRepo;
import com.chat.app.chatApp.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageConsumerTest {
    @Mock
    UserRepo userRepo;
    @Mock
    MessageRepo messageRepo;

    @InjectMocks
    MessageConsumer messageConsumer;
    @Test
    public void consumeMessageTest() {
        MessageRequest messageRequest = getMessageRequest();

        Users sender = new Users();
        sender.setId(2);
        sender.setNickname("john");

        Users receiver = new Users();
        receiver.setId(1);
        receiver.setNickname("jack");
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageRequest.getMessage());

        when(userRepo.findById(1l)).thenReturn(Optional.of(receiver));
        when(userRepo.findById(2l)).thenReturn(Optional.of(sender));
        when(messageRepo.save(message)).thenReturn(message);
        messageConsumer.consumeMessage(messageRequest);
        verify(messageRepo, times(1)).save(eq(message));
    }
    @Test
    public void consumeMessageUserNotFoundTest() {
        MessageRequest messageRequest = getMessageRequest();
        when(userRepo.findById(anyLong())).thenReturn(Optional.empty());
        UserNotFound exception = assertThrows(UserNotFound.class,
                () -> messageConsumer.consumeMessage(messageRequest));
        assertEquals("Sender not found", exception.getMessage());
        verify(userRepo, times(1)).findById(eq(messageRequest.getSenderId()));
    }
    @Test
    public void consumeMessageSelfMessageTest() {
        MessageRequest messageRequest = getMessageRequest();
        Users sender = new Users();
        Users receiver = sender;
        when(userRepo.findById(1l)).thenReturn(Optional.of(receiver));
        when(userRepo.findById(2l)).thenReturn(Optional.of(sender));
        SelfMessageException exception = assertThrows(SelfMessageException.class,
                () ->  messageConsumer.consumeMessage(messageRequest));
        assertEquals("Cannot send a message to yourself", exception.getMessage());
        verify(userRepo, times(1)).findById(eq(messageRequest.getSenderId()));
        verify(userRepo, times(1)).findById(eq(messageRequest.getReceiverId()));
    }
    private static MessageRequest getMessageRequest() {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setReceiverId(1L);
        messageRequest.setSenderId(2l);
        messageRequest.setMessage("hey");
        return messageRequest;
    }
}
