package com.chat.app.chatApp.service;

import com.chat.app.chatApp.constant.ChatConstants;
import com.chat.app.chatApp.entity.Message;
import com.chat.app.chatApp.entity.MessageRequest;
import com.chat.app.chatApp.entity.Users;
import com.chat.app.chatApp.repository.MessageRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MessageServiceImplTest {
    @Mock
    RabbitTemplate rabbitTemplate;
    @Mock
    MessageRepo messageRepo;

    @InjectMocks
    MessageServiceImpl messageService;
    @Test
    public void sendMessageTest() {
        doNothing().when(rabbitTemplate).
                convertAndSend(anyString(), (Object) anyString(), (CorrelationData) any());
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setReceiverId(1L);
        messageRequest.setSenderId(2l);
        messageRequest.setMessage("hey");
        messageService.sendMessage(messageRequest);
        verify(rabbitTemplate, times(1)).convertAndSend(eq(ChatConstants.TOPIC_NAME),
                eq(ChatConstants.ROUTING_KEY),
                eq(messageRequest));
    }
    @Test
    public void viewRecievedMessageTest() {
        Users sender = new Users();
        sender.setId(2);
        sender.setNickname("john");

        Users reciever = new Users();
        reciever.setId(1);
        reciever.setNickname("jack");

        Message message = new Message();
        message.setContent("Hey");
        message.setId(2);
        message.setSender(sender);
        message.setReceiver(reciever);

    when(messageRepo.findByReceiverId(1l)).thenReturn(List.of(message));
    List<Message> messages = messageService.viewReceivedMessages(1l);
        assertFalse(messages.isEmpty());
    }

}
