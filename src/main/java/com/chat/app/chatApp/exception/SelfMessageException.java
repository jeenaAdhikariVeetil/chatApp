package com.chat.app.chatApp.exception;

public class SelfMessageException extends RuntimeException {
    public SelfMessageException(String message) {
        super(message);
    }

    public SelfMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
