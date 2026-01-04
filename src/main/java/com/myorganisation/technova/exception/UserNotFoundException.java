package com.myorganisation.technova.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User doesn't exist");
    }

    public UserNotFoundException(String m) {
        super(m);
    }
}
