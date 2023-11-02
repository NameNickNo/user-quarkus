package com.project.user.exception;

public class UserBadRequestException extends RuntimeException{

    public UserBadRequestException(String message) {
        super(message);
    }
}
