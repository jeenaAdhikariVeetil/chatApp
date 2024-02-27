package com.chat.app.chatApp.service;

import com.chat.app.chatApp.entity.Users;
import com.chat.app.chatApp.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Users addAccount(Users user) {
        return userRepo.save(user);
    }
}
