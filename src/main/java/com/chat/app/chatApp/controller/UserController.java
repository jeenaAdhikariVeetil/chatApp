package com.chat.app.chatApp.controller;

import com.chat.app.chatApp.entity.Users;
import com.chat.app.chatApp.service.UserServiceImpl;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/account")
    public ResponseEntity<Authenticator.Success> createAccount(@RequestBody Users user) {
            userService.addAccount(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
