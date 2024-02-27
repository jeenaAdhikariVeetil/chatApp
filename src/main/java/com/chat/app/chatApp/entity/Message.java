package com.chat.app.chatApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Users sender;

    @ManyToOne
    private Users receiver;

    private String content;
}
