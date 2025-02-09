package com.appdeveloper.app.ws.exceptions;

import java.io.Serial;

public class UserServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5541908747009911669L;

    public UserServiceException(String message) {
        super(message);
    }

}
