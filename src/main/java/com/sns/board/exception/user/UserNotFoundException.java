package com.sns.board.exception.user;

import com.sns.board.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ClientErrorException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User not found");
    }

    public UserNotFoundException(Long userId) {
        super(HttpStatus.NOT_FOUND, "User with userId " + userId + "not found");
    }

    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
