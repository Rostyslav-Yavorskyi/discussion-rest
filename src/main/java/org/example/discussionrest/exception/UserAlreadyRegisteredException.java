package org.example.discussionrest.exception;

public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
