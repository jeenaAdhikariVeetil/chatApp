package com.chat.app.chatApp.exception;

import com.chat.app.chatApp.constant.ChatConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleException(UserNotFound ex) {
        return new ResponseEntity<String>(ChatConstants.USER_ERROR, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(SelfMessageException.class)
    public ResponseEntity<String> handleException(SelfMessageException ex) {
        return new ResponseEntity<String>(ChatConstants.SELF_MESSG_ERROR, HttpStatus.NO_CONTENT);
    }
}
