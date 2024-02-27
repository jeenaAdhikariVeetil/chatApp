package com.chat.app.chatApp.constant;

public class ChatConstants {
    public static final String USER_ERROR="User not found in the Database!";
    public static final String SELF_MESSG_ERROR="You cannot send message to yourself!";
    public static final String QUEUE_NAME = "message_queue";
    public static final String TOPIC_NAME = "message_exchange";
    public static final String ROUTING_KEY = "message_routingKey";
    public static final String AMQP_ERROR = "Internal error while consuming the message";
}
