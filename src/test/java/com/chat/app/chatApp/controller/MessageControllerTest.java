package com.chat.app.chatApp.controller;

import com.chat.app.chatApp.entity.MessageRequest;
import com.chat.app.chatApp.service.MessageServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    MessageServiceImpl messageService;
    @InjectMocks
    MessageController messageController;
    @Test
    void contexLoads() {
        assertThat(messageController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void sendMessageTest() throws Exception {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setSenderId(1l);
        messageRequest.setReceiverId(2l);
        messageRequest.setMessage("Hello there");
        mockMvc.perform(post("/chat/message").contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(new ObjectMapper().writeValueAsString(messageRequest))).
                andDo(print()).andExpect(status().isCreated());
    }
}
