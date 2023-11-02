package com.project.user.exception;

public class UserNotCreateException extends UserBadRequestException{
    public UserNotCreateException(String message) {
        super(message);
    }
}
