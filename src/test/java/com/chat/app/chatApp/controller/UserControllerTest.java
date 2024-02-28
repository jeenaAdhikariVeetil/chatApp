package com.chat.app.chatApp.controller;

import com.chat.app.chatApp.entity.Users;
import com.chat.app.chatApp.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    UserServiceImpl userService;
    @InjectMocks
    UserController userController;
    @Test
    void contexLoads() {
        assertThat(userController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void createUserTest() throws Exception {
        Users user = new Users();
        user.setId(1l);
        user.setNickname("nick");
        mockMvc.perform(post("/user/account").contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(new ObjectMapper().writeValueAsString(user))).
                andDo(print()).andExpect(status().isCreated());
    }
}
