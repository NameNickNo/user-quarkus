package org.acme.exception;

public class UserEmailNotUniqueException extends UserBadRequestException {
    public UserEmailNotUniqueException(String message) {
        super(message);
    }
}
